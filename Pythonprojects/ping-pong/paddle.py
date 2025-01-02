from turtle import Turtle


class Paddle(Turtle):

    def __init__(self, position):
        super().__init__()
        self.shape("square")
        self.color("white")
        self.pu()
        self.goto(position)
        self.setheading(90)
        self.shapesize(stretch_len=5)

    def l_go_up(self):
        if self.ycor() < 250:
            self.forward(20)

    def l_go_down(self):
        if self.ycor() > -250:
            self.backward(20)

    def r_go_up(self):
        if self.ycor() < 250:
            self.forward(20)

    def r_go_down(self):
        if self.ycor() > -250:
            self.backward(20)
