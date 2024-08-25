# Lottery Game

• Overview

The Lottery Game is a console-based Java application where players can register, place bets, and participate in a lottery draw. Players can choose to either generate random numbers (Surpresinha mode) or manually input their numbers. The game supports multiple players and manages bets, performs a lottery draw, and determines winners based on drawn numbers.

• Project Structure

The project consists of several classes, each responsible for different functionalities:

Bet: Represents a bet made by a player. It includes bet details such as the bet ID, numbers selected, and static values related to betting.

Game: The main class that controls the flow of the game, including player registration, bet management, and game execution.

Player: Represents a player in the game. It contains player information such as name, CPF (Brazilian ID), and their bets.

PlayerSorter: Provides functionality to sort players alphabetically by name.

Round: Handles the lottery round, including generating winning numbers and managing the round ID.

• How to Run

Compile the Code: Navigate to the directory containing the Java files and compile them using the javac command:

    javac *.java
  
   Run the Application: Execute the Game class to start the application:

    java Game

• Notes

Ensure that you have Java installed and configured on your system to compile and run the application.

The application uses a console-based interface for interaction. Follow the prompts to play the game.

• License

This project is for educational purposes and job application demonstration. It is not intended for commercial use.
    
