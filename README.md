# TheaterTicketDispenserSimulation

**TheaterTicketDispenserSimulation** is a Java application designed to simulate an automatic theater ticket dispenser. It enables users to select languages, choose tickets by date and zone, select seats, and pay with a credit card.

This project was developed using NetBeans IDE, as the final project for an Object-Oriented Programming course, showcasing the application of OOP principles, design patterns, and Java programming skills.

## Features

- Multilingual interface for user interaction.
- Ticket selection by date, zone, and seating preferences.
- Integrated credit card payment functionality.
- Utilizes interfaces provided by the professor to standardize and modularize the application design.
- Data persistence through Java's Serializable class for saving and retrieving state across sessions.

## Additional Features and Specifications

- **Credit Card Retention**: If a credit card is not retrieved within 30 seconds after payment, the Dispenser will retain it to ensure security.
- **Auto-Return to Welcome Screen**: The Dispenser automatically returns to the Welcome Screen after 10 seconds of inactivity, enhancing user experience and system efficiency.
- **Cancel Button Functionality**: Anytime the cancel button is pressed, the system immediately navigates back to the Welcome Screen, allowing users to easily start over.
- **No Theater Passes on Mondays**: To maintain exclusivity of the play, no theater passes are issued on Mondays.
- **Single Play Focus**: The theater offers a singular, high-quality play, ensuring a dedicated and tailored experience for all attendees.
- **Unique Dispenser**: There is only one ticket dispenser in the theater, designed to be efficient and user-friendly, streamlining the ticket purchasing process for attendees.
- **Seat Selection Limit**: To ensure fair access and manageability, a maximum of four seats can be selected per transaction, allowing groups and families to book together while maintaining availability for other patrons.
- **Random Transaction Errors**: Occasionally, due to simulated network issues, there can be a random error in the connection with the bank during credit card transactions, reflecting real-world scenarios of payment processing.

## Prerequisites

- Java JDK 8 or higher.

## Preview
### Welcome Screen
![image](https://github.com/TarekElshami/TheaterTicketDispenserSimulation/assets/117302441/a1e40703-86f9-494b-a3a8-448f535265e1)
***
### Lenguage Selection Screen
![image](https://github.com/TarekElshami/TheaterTicketDispenserSimulation/assets/117302441/4913ef93-d606-448f-880a-af2169245b50)
***
### Day Selection Screen
![image](https://github.com/TarekElshami/TheaterTicketDispenserSimulation/assets/117302441/9599de43-7cbd-49b3-8ab8-4e4e93c9a1dc)
***
### Area Selection Screen
![image](https://github.com/TarekElshami/TheaterTicketDispenserSimulation/assets/117302441/58d904b0-49f6-4388-9b7c-4f2ee49b4ab4)
***
### Seat Selection Screen (Box 1)
![image](https://github.com/TarekElshami/TheaterTicketDispenserSimulation/assets/117302441/dfd7c7c7-c52b-4999-8516-0c5024b36b08)
***
### Summary and card request screen
![image](https://github.com/TarekElshami/TheaterTicketDispenserSimulation/assets/117302441/04252816-0072-4fd9-968b-76a8f729e636)
***
### Ticket
![image](https://github.com/TarekElshami/TheaterTicketDispenserSimulation/assets/117302441/63d91661-4c52-4927-aa5d-0f181adba526)
***
