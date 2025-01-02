from tkinter import *
import pandas
import random

BACKGROUND_COLOR = "#B1DDC6"

try:
    words = pandas.read_csv("data/words_to_learn.csv")
except FileNotFoundError:
    original_words = pandas.read_csv("data/french_words.csv")
    to_learn = original_words.to_dict(orient="records")
else:
    to_learn = words.to_dict(orient="records")

current_card = {}


def next_card():
    global current_card, timer
    window.after_cancel(timer)
    current_card = random.choice(to_learn)
    canvas.itemconfig(card_title, text="French", fill="black")
    canvas.itemconfig(card_word, text=f'{current_card["French"]}', fill="black")
    canvas.itemconfig(background_image, image=front_img)
    timer = window.after(3000, flip_card)


def flip_card():
    global current_card, timer
    canvas.itemconfig(card_title, text="English", fill="white")
    canvas.itemconfig(card_word, text=f'{current_card["English"]}', fill="white")
    canvas.itemconfig(background_image, image=back_img)
    window.after_cancel(timer)


def known():
    to_learn.remove(current_card)
    data = pandas.DataFrame(to_learn)
    data.to_csv("data/words_to_learn.csv", index=False)
    print(len(to_learn))
    next_card()


window = Tk()
window.title("Flash Game")
window.config(padx=50, pady=50, bg=BACKGROUND_COLOR)

front_img = PhotoImage(file="images/card_front.png")
back_img = PhotoImage(file="images/card_back.png")
canvas = Canvas(width=800, height=526, highlightthickness=0, bg=BACKGROUND_COLOR)
background_image = canvas.create_image(400, 263, image=front_img)
card_title = canvas.create_text(400, 150, text="", font=("Ariel", 40, "italic"))
card_word = canvas.create_text(400, 263, text="", font=("Ariel", 60, "bold"))
canvas.grid(column=0, row=0, columnspan=2)

wrong_image = PhotoImage(file="images/wrong.png")
wrong = Button(image=wrong_image, highlightthickness=0)
wrong.grid(column=0, row=1)
wrong.config(command=next_card)

right_image = PhotoImage(file="images/right.png")
right = Button(image=right_image, highlightthickness=0)
right.grid(column=1, row=1)
right.config(command=known)

timer = window.after(10, func=next_card)

window.mainloop()
