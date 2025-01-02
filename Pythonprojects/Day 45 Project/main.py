from bs4 import BeautifulSoup
import requests

response = requests.get("https://web.archive.org/web/20200518073855/https://www.empireonline.com/movies/features/best-movies-2/")
movies_page = response.text

soup = BeautifulSoup(movies_page, "html.parser")

movie_titles = soup.find_all(name="h3", class_="title")

title_list = [title.getText() for title in movie_titles[::-1]]

with open("./Day 45 Project/movies.txt", "w", encoding="utf-8") as f:
    for title in title_list:
        f.write(f"{title}\n")