# War of Cards

**War of Cards** is a turn-based card game built for Android. The game allows players to compete against each other using a deck of cards, selecting the best card for each round to win points. The game supports both single-player and multiplayer modes, where each player has a profile with personal details, a coin count, and a collection of cards. Players can purchase additional cards from a card shop using coins earned from winning games.

## Table of Contents

* [Features](#features)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
  * [Configuration](#configuration)
* [Usage](#usage)
  * [Gameplay](#gameplay)
  * [Card Shop](#card-shop)
  * [Sound Effects](#sound-effects)
  * [Animations](#animations)
* [Firebase Integration](#firebase-integration)
* [Project Structure](#project-structure)
* [Contributing](#contributing)
* [License](#license)
* [Acknowledgements](#acknowledgements)

## Features

* **Player Profiles:** Each player has a unique profile with a name, phone number, coin count, and card collection.
* **Card Selection:** Players select three cards before starting the game. Each card has unique attributes such as name, price, value, and image.
* **Turn-Based Gameplay:** Players choose one card per round to compete. The winner of each round earns 1000 points.
* **Card Shop:** Players can purchase new cards using coins. The shop displays all 10 available cards, with a dynamic slider for browsing.
* **Sound Effects:** Includes sound effects for game start, rounds, and other game events.
* **Animations:** Card animations are implemented using LottieFiles, including drag-and-drop functionality.
* **Firebase Integration:** The game uses Firebase for authentication, player data management, and storing player profiles and cards.

## Getting Started

### Prerequisites

* Android Studio (latest version recommended)
* Firebase account for database and authentication setup

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/war-of-cards.git

## Configuration

### Set up Firebase:

1. Go to the [Firebase Console](https://console.firebase.google.com/).
2. Create a new project.
3. Add an Android app to your Firebase project and follow the setup instructions.
4. Download the `google-services.json` file and place it in the `app/` directory.

### Update Dependencies:

- Ensure all necessary dependencies are included in the `build.gradle` files.

### Set up Lottie Animations:

- Place your Lottie JSON files in the `res/raw/` directory.

## Usage

### Gameplay

- Players select three cards from their collection before starting the game.
- During each round, players choose one card to play. The player with the highest value card wins the round and earns 1000 points.

### Card Shop

- Players can purchase additional cards using coins.
- The shop displays all available cards with a dynamic slider for browsing.

### Sound Effects

- Sound effects play during various game events:
  - Game start
  - Each round
  - Victory and defeat

## Firebase Integration

The game uses Firebase for:

- User authentication
- Storing player profiles
- Managing the cards each player owns
- Tracking coin balances
