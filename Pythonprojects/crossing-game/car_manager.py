import random
from turtle import Turtle

COLORS = ["red", "orange", "yellow", "green", "blue", "purple"]
STARTING_MOVE_DISTANCE = 5
MOVE_INCREMENT = 10


class CarManager(Turtle):

    def __init__(self):
        super().__init__()
        self.cars = []
        self.forward_distance = STARTING_MOVE_DISTANCE
        self.hideturtle()

    def generate(self):
        should_i = random.randint(1, 20)
        if should_i % 5 == 0:
            car = Turtle()
            car.pu()
            car.shape("square")
            car.color(random.choice(COLORS))
            car.goto(300, random.randint(-260, 260))
            car.setheading(180)
            car.shapesize(stretch_len=2)
            self.cars.append(car)

    def move(self):
        for car in self.cars:
            car.forward(self.forward_distance)

    def faster(self):
        self.forward_distance += MOVE_INCREMENT
