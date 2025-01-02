from bs4 import BeautifulSoup
import requests
import spotipy
from spotipy.oauth2 import SpotifyOAuth

def get_uri(sp, song):
    find = sp.search(q=song, type="track")
    tracks = find['tracks']['items']
    uri = tracks[0]['uri']
    return uri

def get_playlist_names(sp):
    playlists = sp.current_user_playlists()
    playlist_names = []
    for item in playlists['items']:
        playlist_names.append(item['name'])
    return playlist_names

def create_playlist(sp, date):
    playlist_name = date + " Billboard 100"
    user_playlists = get_playlist_names(sp)
    if playlist_name not in user_playlists:
        user_id = sp.current_user()['id']
        sp.user_playlist_create(user_id, playlist_name, public=False)
        print("Playlist successfully created")
    else:
        print("Playlist exists")

def get_playlist_id(sp, date):
    playlist_name = date + " Billboard 100"
    playlists = sp.current_user_playlists()
    for playlist in playlists['items']:
        if playlist['name'] == playlist_name:
            return playlist['id']


date = input("Which year do you want to travel to? Type the date in this format YYYY-MM-DD: ")

s = "https://www.billboard.com/charts/hot-100/" + date

header = {"User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:131.0) Gecko/20100101 Firefox/131.0"}

response = requests.get(s, headers=header)

songs_page = response.text

soup = BeautifulSoup(songs_page, "html.parser")

songs_titles_web = soup.select("li h3")
songs_titles_web = songs_titles_web[:100]

songs_titles = []
for songs in songs_titles_web:
    songs_titles.append(songs.getText().strip())

CLIENT_ID = "7b3fbae5f953449c884884ab4356e229"
CLIENT_SECRET = "6e3daf5c5c4447efa605c6d3e1464319"

sp = spotipy.Spotify(auth_manager=SpotifyOAuth(client_id=CLIENT_ID,
                                               client_secret=CLIENT_SECRET,
                                               redirect_uri="http://example.com",
                                               scope="user-read-email user-read-private playlist-read-private playlist-modify-private"))

user_id = sp.current_user()['id']
create_playlist(sp, date)
playlist_id = get_playlist_id(sp, date)
uri_list = [get_uri(sp, song) for song in songs_titles]
sp.user_playlist_add_tracks(user_id, playlist_id, uri_list)

print("All songs Added")
