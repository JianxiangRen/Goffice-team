class User:
    def __init__(self, userID, password, firstname, lastname, emailaddr):
        self.userID = userID
        self.password = password
        self.firstname = firstname
        self.lastname = lastname
        self.emailaddr = emailaddr

    def __repr__(self):
        return f"User({self.userID}, {self.firstname}, {self.lastname}, {self.emailaddr})"

def sort_and_organize_users(users):
    # Sort the users by userID
    sorted_users = sorted(users, key=lambda user: user.userID)

    # Organize users into a dictionary with uppercase keys
    organized_users = {}
    for user in sorted_users:
        first_char = user.userID[0].upper()
        if first_char not in organized_users:
            organized_users[first_char] = []
        organized_users[first_char].append(user.userID)  # Storing userID; change as needed

    return organized_users


# Example usage
users = [
    User("Alice123", "pass1", "Alice", "Johnson", "alice@example.com"),
    User("bob456", "pass2", "Bob", "Smith", "bob@example.com"),
    User("charlie789", "pass3", "Charlie", "Brown", "charlie@example.com"),
    User("2Anna001", "pass4", "Anna", "Davis", "anna@example.com"),
    User("1ZoeZebra", "pass5", "Zoe", "Zebra", "zoe@example.com")
]

organized_users = sort_and_organize_users(users)
print(organized_users)
