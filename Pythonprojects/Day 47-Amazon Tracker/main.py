import requests
import os
from bs4 import BeautifulSoup
import smtplib
from dotenv import load_dotenv

load_dotenv()

EMAIL_ADDRESS = os.getenv("EMAIL_ADDRESS")
EMAIL_PASSWORD = os.getenv("EMAIL_PASSWORD")
SMTP_ADDRESS = os.getenv("SMTP_ADDRESS")
WEBSITE = os.getenv("AMAZON_WEBSITE")

def send_email(EMAIL_ADDRESS, EMAIL_PASSWORD, SMTP_ADDRESS, price):
    text = f"The product price is now {price}, below your target price."
    with smtplib.SMTP(SMTP_ADDRESS, 587) as connection:
        connection.starttls()
        connection.login(user=EMAIL_ADDRESS, password=EMAIL_PASSWORD)
        connection.sendmail(from_addr=EMAIL_ADDRESS, to_addrs="harshdhiran2604@gmail.com",
                                    msg=f"Subject: Amazon Price Alert\n\n{text}")
        


header = {"User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36"}

response = requests.get(WEBSITE, headers=header)

amazon_page = response.text

soup = BeautifulSoup(amazon_page, "html.parser")

whole_price = soup.find("span", class_="a-price-whole")
fraction_price = soup.find("span", class_="a-price-fraction")
price = whole_price.getText() + fraction_price.getText()

target_price = float(input("Enter your target price: "))
if target_price >= float(price):
    send_email(EMAIL_ADDRESS, EMAIL_PASSWORD, SMTP_ADDRESS, price)
