from tkinter import *
from tkinter import messagebox
import random
import pyperclip


# ---------------------------- PASSWORD GENERATOR ------------------------------- #
def generate_password():
    pwd.delete(0, END)
    # Password Generator Project
    letters = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
               'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
               'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
    numbers = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']
    symbols = ['!', '#', '$', '%', '&', '(', ')', '*', '+']

    password_list = []

    for char in range(random.randint(5, 8)):
        password_list.append(random.choice(letters))

    for char in range(random.randint(1, 3)):
        password_list += random.choice(symbols)

    for char in range(random.randint(2, 4)):
        password_list += random.choice(numbers)

    random.shuffle(password_list)

    password = "".join(password_list)

    pwd.insert(0, f"{password}")

    pyperclip.copy(f"{password}")

    messagebox.askokcancel(message="Password has been copied")


# ---------------------------- SAVE PASSWORD ------------------------------- #
def add_details():
    web = website.get()
    email = name.get()
    password = pwd.get()
    if len(web) < 1 or len(email) < 5 or len(password) < 1:
        messagebox.showinfo(title="Invalid Details", message="Please enter valid details")
        return
    is_ok = messagebox.askokcancel(title="Save Details", message=f"Details entered:\n\nWebsite: {web}\n"
                                                                 f"Email/Username: {email}\nPassword: {password}")
    if is_ok:
        with open(file="password.txt", mode="a") as file:
            file.write(f"{web} | {email} | {password}\n")
            website.delete(0, END)
            pwd.delete(0, END)


# ---------------------------- UI SETUP ------------------------------- #
window = Tk()
window.title("Password Manager")
window.config(padx=20, pady=20)

canvas = Canvas(width=200, height=200)
img = PhotoImage(file="logo.png")
canvas.create_image(100, 100, image=img)
canvas.grid(column=1, row=0)

label1 = Label(text="Website:")
label1.grid(column=0, row=1)
label2 = Label(text="Email/Username:")
label2.grid(column=0, row=2)
label3 = Label(text="Password:")
label3.grid(column=0, row=3)

website = Entry(width=52)
website.grid(row=1, column=1, columnspan=2)
website.focus()
name = Entry(width=52)
name.insert(0, "harshdhiran2004@gmail.com")
name.grid(row=2, column=1, columnspan=2)
pwd = Entry(width=33)
pwd.grid(row=3, column=1)

gen = Button(text="Generate Password")
gen.grid(row=3, column=2)
gen.config(command=generate_password)

add = Button(text="Add", width=44)
add.grid(row=4, column=1, columnspan=2)
add.config(command=add_details)

window.mainloop()
