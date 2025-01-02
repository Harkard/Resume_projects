from turtle import Turtle


class Scoreboard(Turtle):

    def __init__(self):
        super().__init__()
        self.score = 0
        self.highscore = 0
        self.pu()
        self.speed("fastest")
        self.color("white")
        self.goto(0, 260)
        self.hideturtle()
        self.display()

    def reset(self):
        if self.score > self.highscore:
            self.highscore = self.score
        self.score = 0
        with open("G:/PythonProjects/snake-game/score.txt", mode="r") as file:
            if self.highscore > int(file.read()):
                with open("Score.txt", mode="w") as f:
                    f.write(f"{self.highscore}")
        self.display()

    # def game_over(self):
    #     self.goto(0, 0)
    #     self.write(f"Game Over", align="center", font=('Courier', 15, 'bold'))

    def display(self):
        self.clear()
        with open("G:/PythonProjects/snake-game/score.txt", "r") as file:
            self.highscore = int(file.read())
        self.write(f"Score: {self.score} High Score: {self.highscore}", align="center", font=('Courier', 15, 'bold'))

    def update(self):
        self.score += 1
        self.display()
