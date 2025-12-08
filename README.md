# Space Invaders - Design Patterns Project

## 📋 Description

This is a Space Invaders-style video game developed as part of the Design Patterns course project. The game demonstrates the practical implementation of multiple design patterns in a real software development context.

**Course**: Design Patterns
**Academic Year**: 2025-2026
**Instructor**: Haythem Ghazouani

## 👥 Group Members

- [Your Name Here]
- [Team Member 2]
- [Team Member 3]

## 🎮 Game Features

- Classic Space Invaders gameplay with modern design patterns
- Multiple game states (Menu, Playing, Paused, Game Over, Victory)
- Power-up system with multiple enhancements
- Enemy AI with pattern-based behavior
- Score tracking and high score system
- Complete event logging system

## 🏗️ Design Patterns Implemented

### 1. **State Pattern** (OBLIGATORY)
- **Game States**: Manages different game states (Menu, Playing, Paused, GameOver, Victory)
  - Files: `patterns/state/GameState.java`, `patterns/state/MenuState.java`, `patterns/state/PlayingState.java`, etc.
- **Player States**: Manages player character states (Idle, Moving, Shooting)
  - Files: `patterns/state/PlayerState.java`, `patterns/state/IdlePlayerState.java`, etc.
- **Purpose**: Cleanly separates behavior based on current state

### 2. **Decorator Pattern** (OBLIGATORY)
- **Power-ups**: Dynamically adds abilities to the player
  - `SpeedBoostDecorator`: Doubles movement speed
  - `ShieldDecorator`: Adds protective shield
  - `FirePowerDecorator`: Increases fire power
- **Files**: `patterns/decorator/PowerUpDecorator.java` and concrete decorators
- **Purpose**: Adds functionality to objects dynamically without modifying their structure
- **Logging**: All decorator applications and removals are logged

### 3. **Composite Pattern** (OBLIGATORY)
- **Game Hierarchy**: Organizes game elements in a tree structure
  - `GameLevel`: Composite that contains multiple game components
  - `Enemy`, `PowerUp`: Leaf components
- **Files**: `patterns/composite/GameComponent.java`, `patterns/composite/GameLevel.java`
- **Purpose**: Treats individual objects and compositions uniformly

### 4. **Factory Pattern** (ADDITIONAL)
- **Entity Creation**: Centralized creation of game entities
  - `EnemyFactory`: Creates enemy instances
  - `PowerUpFactory`: Creates power-up instances
- **Files**: `patterns/factory/EntityFactory.java` and concrete factories
- **Purpose**: Encapsulates object creation logic

### 5. **Singleton Pattern** (ADDITIONAL)
- **GameLogger**: Single logging instance throughout the application
- **GameContext**: Single game context managing global state
- **Files**: `patterns/singleton/GameLogger.java`, `patterns/singleton/GameContext.java`
- **Purpose**: Ensures only one instance exists and provides global access point

## 🛠️ Technologies Used

- **Language**: Java 17
- **GUI Framework**: JavaFX 21.0.1
- **Logging**: Log4j2 2.22.0
- **Build Tool**: Maven 3.6+
- **Version Control**: Git

## 📦 Installation

### Prerequisites

- JDK 17 or higher
- Maven 3.6+

### Steps

1. **Clone the repository**:
```bash
git clone [YOUR_REPOSITORY_URL]
cd projet
```

2. **Compile the project**:
```bash
mvn clean compile
```

3. **Run the game**:
```bash
mvn javafx:run
```

### Alternative: Create executable JAR

```bash
mvn clean package
java -jar target/space-invaders-game-1.0-SNAPSHOT.jar
```

## 🎯 How to Play

### Basic Controls

- **Arrow Left/Right**: Move the player
- **SPACE**: Shoot bullets
- **P**: Pause game
- **ESC**: Return to menu / Quit
- **ENTER**: Start game / Confirm selection

### Power-Up Test Keys (Decorator Pattern Demo)

Press these keys **during gameplay** to activate power-ups instantly:
- **1**: Speed Boost (5 sec) - 🟡 Yellow glow, move 2x faster
- **2**: Shield (10 sec) - 🔵 Cyan bubble, +3 defense
- **3**: Fire Power (8 sec) - 🟠 Orange glow, shoot 2-3 bullets

💡 **Demo Tip**: Press 1, 2, and 3 together to see all decorators stacking!

### Gameplay

1. Start from the main menu
2. Destroy all enemies to win
3. **Test the Decorator Pattern**: Press 1, 2, or 3 to activate power-ups
4. Watch the HUD (top-left) to see active power-ups with timers
5. Shoot enemies to increase your score (100 points each)
6. Try to beat your high score!

## 📁 Project Structure

```
projet/
├── src/
│   ├── main/
│   │   ├── java/com/polytech/game/
│   │   │   ├── Main.java                    # Application entry point
│   │   │   ├── patterns/
│   │   │   │   ├── state/                   # State Pattern implementation
│   │   │   │   ├── decorator/               # Decorator Pattern implementation
│   │   │   │   ├── composite/               # Composite Pattern implementation
│   │   │   │   ├── factory/                 # Factory Pattern implementation
│   │   │   │   └── singleton/               # Singleton Pattern implementation
│   │   │   ├── entities/
│   │   │   │   └── Player.java             # Player entity
│   │   │   └── gui/
│   │   │       └── GameWindow.java         # Main game window
│   │   └── resources/
│   │       └── log4j2.xml                  # Logging configuration
│   └── test/                               # Unit tests
├── logs/
│   └── game.log                            # Game event logs
├── pom.xml                                 # Maven configuration
├── README.md                               # This file
└── .gitignore                              # Git ignore rules
```

## 📝 Logging System

The game implements a comprehensive logging system that tracks:

### Events Logged

1. **State Changes**:
   - Game state transitions (Menu → Playing → Paused, etc.)
   - Player state transitions (Idle → Moving → Shooting)
   - Timestamp for each transition

2. **Decorator Applications**:
   - Type of decorator applied
   - Target object
   - Duration of effect
   - Application and removal timestamps

3. **Game Events**:
   - Game start/end
   - Entity creation/destruction
   - Score updates
   - Level changes

### Log Format

```
[2024-12-15 14:23:45] [INFO] Game started
[2024-12-15 14:23:47] [STATE] Game: MENU -> PLAYING
[2024-12-15 14:23:50] [STATE] Player: IDLE -> MOVING
[2024-12-15 14:23:52] [DECORATOR] SpeedBoostDecorator applied to BaseCharacter
[2024-12-15 14:24:02] [DECORATOR] SpeedBoostDecorator removed from Player
```

### Log Location

- **File**: `logs/game.log`
- **Console**: Real-time output to console

## 🏛️ Architecture

The project follows clean architecture principles with clear separation of concerns:

- **Patterns Layer**: Contains all design pattern implementations
- **Entities Layer**: Game objects and business logic
- **GUI Layer**: User interface and rendering
- **Utils Layer**: Utility classes and helpers

## 🧪 Testing

Run unit tests:
```bash
mvn test
```

## 📊 UML Class Diagram

The complete UML class diagram showing the architecture and design patterns is available in:
- `docs/class-diagram.png` (or create using the instructions below)

### Generate UML Diagram

You can generate the UML diagram using tools like:
- **PlantUML**: Code-based diagram generation
- **IntelliJ IDEA**: Built-in diagram generator (Right-click on package → Diagrams → Show Diagram)
- **draw.io**: Manual diagram creation

## 🐛 Known Issues

- None currently reported

## 🔮 Future Enhancements

- Multiple levels with increasing difficulty
- More enemy types with different behaviors
- Additional power-ups
- Sound effects and background music
- Local multiplayer mode
- High score persistence

## 📚 References

- **Design Patterns**: Gang of Four - "Design Patterns: Elements of Reusable Object-Oriented Software"
- **Refactoring Guru**: https://refactoring.guru/design-patterns
- **JavaFX Documentation**: https://openjfx.io/
- **Game Programming Patterns**: https://gameprogrammingpatterns.com/

## 📄 License

This project is for educational purposes as part of the Design Patterns course.

## 🙏 Acknowledgments

- **Instructor**: Haythem Ghazouani
- **Course**: Design Patterns 2025-2026
- **Institution**: Polytech

---

**Note**: This project demonstrates the practical application of design patterns in software development. Each pattern is implemented to solve a specific design problem, making the codebase maintainable, extensible, and following SOLID principles.

## 🚀 Quick Start Guide

For first-time users:

1. Ensure Java 17+ is installed: `java -version`
2. Ensure Maven is installed: `mvn -version`
3. Clone and navigate to project directory
4. Run: `mvn javafx:run`
5. Enjoy the game!

For any questions or issues, please contact the development team.
