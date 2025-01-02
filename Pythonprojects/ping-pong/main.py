import time
from turtle import Screen
from paddle import Paddle
from ball import Ball
from scoreboard import Scoreboard

screen = Screen()
screen.setup(height=600, width=800)
screen.bgcolor("black")
screen.title("Pong Game")
screen.tracer(0)

l_paddle = Paddle((350, 0))
r_paddle = Paddle((-350, 0))

ball = Ball()
scoreboard = Scoreboard()

screen.listen()
screen.onkeypress(fun=l_paddle.l_go_up, key="Up")
screen.onkeypress(fun=l_paddle.l_go_down, key="Down")
screen.onkeypress(fun=r_paddle.r_go_up, key="w")
screen.onkeypress(fun=r_paddle.r_go_down, key="s")

speed = 1
game_on = True
while game_on:
    time.sleep(0.1*speed)
    screen.update()
    ball.move()
    if ball.ycor() > 280 or ball.ycor() < -280:
        ball.bounce()

    if ball.xcor() > 325 or ball.xcor() < -325:
        if ball.distance(l_paddle) < 50 or ball.distance(r_paddle) < 50:
            ball.contact()
            speed *= 0.85

    if ball.xcor() > 380:
        ball.restart()
        scoreboard.r_point()
        speed = 1
    elif ball.xcor() < -380:
        ball.restart()
        scoreboard.l_point()
        speed = 1


screen.exitonclick()
