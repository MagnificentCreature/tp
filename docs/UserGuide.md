---
layout: page
title: User Guide
---

Connectify is a **desktop app for managing clients, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Connectify can get your client management tasks done faster than traditional GUI apps.

![Ui Markup](images/Ui.png)

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start [coming soon]

--------------------------------------------------------------------------------------------------------------------

## Features [coming soon]

### Viewing help [coming soon]

### Adding a client profile: `create profile` [to be implemented]

Creates a new client profile with various details.

Format
```text
create profile <client_name> --profession <profession> --email <email>
--telegram <telegram_handle> --phone <phone_number> --income <income>
--details <additional_details>
```

Example
```text
create profile John Doe --profession Sales --email john@example.com
--telegram @john_doe --phone +1234567890 --income $50,000
--details "Birthday: 01/15, Family: Spouse, 2 children"
```

Acceptable values
- <client_name>: Alphanumeric, up to 50 characters, has to be unique
- <profession>: Alphanumeric, up to 50 characters
- <email>: Valid email address format
- <telegram_handle>: Alphanumeric, up to 50 characters, starting with '@'
- <phone_number>: Numeric, valid phone number format
- <income>: Numeric, representing annual income
- <additional_details>: Alphanumeric, additional client details

Expected Output (Success)
- The new client profile is created and added to the address book
- The GUI should reflect the newly added client profile

Expected Output (Failure)
- Invalid email, telegram handle, or phone number format: "Invalid email/telegram/phone format."
- Missing required parameters: "Missing parameters. Please provide all required details."

### Marking a client as Cold, Warm, or Hot Leads [to be implemented]

Tag a client as a "hot lead", "warm lead", or "cold lead" to prioritise your interactions.

Format
```text
lead <lead_category> <client_name>
```

Example
```text
lead hot John Doe
```

Acceptable Values
- <lead_category>: "hot", "warm" or "cold"
- <client_name>: Alphanumeric, the name of an existing client

Expected Output (Success)
- The specified client is marked with the chosen lead category
- The GUI should display the updated lead category for the client

Expected Output (Failure)
- Invalid lead category: "Invalid lead category. Please choose 'hot,' 'warm,' or 'cold.'"
- Client not found: "Client not found in the address book."

### Delete a client profile [to be implemented]

Delete a client's profile from the address book.

Format
```text
delete profile <client_name>
```

Example
```text
delete profile John Doe
```

Acceptable Values:
- <client_name>: Alphanumeric, the name of an existing client

Expected Output (Success)
- The specified client profile is deleted from the address book
- The GUI should reflect the removal of the client profile

- Expected Output (Failure)
- Client not found: "Client not found in the address book."
- Missing client name parameter: "Please enter the client name."

### Viewing a client profile [to be implemented]

View the full details of a client profile.

Format
```text
view <index>
```

```text
view 1
```

Acceptable Values
- <index>: Number, the index of the client to view in the list displayed.

Expected Output (Success)
- The full details of the selected client profile are displayed in the GUI
 
Expected Output (Failure)
- Invalid index: "The person index provided is invalid"

### Create a client interaction [to be implemented]

Create an interaction that is tagged to a client.

Format
```text
log <client_name> <interaction>
```

Example 
```text
log John Doe “Discussed financial plans”
```

Acceptable Values
- <client_name>: Alphanumeric, the name of an existing client
- <interaction>: Alphanumeric, details of interaction with the client

Expected Output (Success)
- The client profile is shown
- The interaction is added to the client profile

Expected Output (Failure)
- Client not found: "Client not found in the address book."
- Missing client name parameter: "Please enter the client name."
- Missing interaction parameter: “Please enter the client interaction.”

--------------------------------------------------------------------------------------------------------------------

## FAQ [coming soon]


--------------------------------------------------------------------------------------------------------------------

## Known issues [coming soon]

--------------------------------------------------------------------------------------------------------------------

## Command summary [coming soon]
