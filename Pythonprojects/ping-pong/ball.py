from turtle import Turtle


class Ball(Turtle):

    def __init__(self):
        super().__init__()
        self.shape("circle")
        self.color("white")
        self.pu()
        self.speed("slowest")
        self.goto(0, 0)
        self.x = 10
        self.y = 10

    def move(self):
        new_x = self.xcor() + self.x
        new_y = self.ycor() + self.y
        self.goto(new_x, new_y)

    def bounce(self):
        self.y *= -1

    def contact(self):
        self.x *= -1

    def restart(self):
        self.goto(0, 0)
        self.x *= -1
        self.y *= -1
