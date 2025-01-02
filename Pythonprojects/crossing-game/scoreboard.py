from turtle import Turtle

FONT = ("Courier", 24, "normal")


class Scoreboard(Turtle):

    def __init__(self):
        super().__init__()
        self.pu()
        self.hideturtle()
        self.score = 0
        self.goto(-280, 250)
        self.print_score()

    def print_score(self):
        self.write(f"Score: {self.score}", align="left", font=FONT)

    def increase_score(self):
        self.score += 1
        self.clear()
        self.print_score()
