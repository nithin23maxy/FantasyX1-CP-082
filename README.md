
# 🏏 FantasyXI - Fantasy Cricket Team Builder

FantasyXI is a menu-driven, console-based Java application that allows users to create and manage their own fantasy cricket teams.

Users can select players, build a team within a fixed credit budget, validate team combinations, assign a Captain and Vice-Captain, calculate fantasy points, and view a leaderboard.

The project demonstrates core Java programming concepts and Object-Oriented Programming (OOP).

## 🎯 Project Objectives

- Build a fantasy cricket team with 11 players.
- Maintain a maximum budget of 100 credits.
- Select players based on their roles.
- Restrict a maximum of 7 players from one real-world team.
- Assign Captain and Vice-Captain.
- Calculate fantasy points based on player performance.
- Display a fantasy points leaderboard.
- Save and load team details using file handling.

## ✨ Features

1. **View Available Players** - Display the list of available cricket players.
2. **Add Players** - Select players for your fantasy team.
3. **View My Team** - Display selected players and their details.
4. **Calculate Points** - Calculate fantasy points based on runs, wickets and catches.
5. **Leaderboard** - Display players ranked by fantasy points.
6. **Remove Players** - Remove selected players from the team.
7. **Team Validation** - Check player count, credits, roles and team limits.
8. **Captain Selection** - Assign a Captain.
9. **Vice-Captain Selection** - Assign a Vice-Captain.
10. **Captain Details** - View team leadership information.
11. **Save Team** - Store team details in a text file.
12. **Load Team** - Restore previously saved team details.

## 🛠️ Technologies Used

- Java
- Object-Oriented Programming
- ArrayList
- HashMap
- Scanner
- File Handling
- Exception Handling

## 📋 Team Selection Rules

| Rule | Requirement |
|---|---|
| Total Players | Exactly 11 |
| Maximum Credits | 100 |
| Maximum Players from One Team | 7 |
| Minimum Batters | 3 |
| Minimum Bowlers | 3 |
| Minimum All-Rounders | 1 |
| Minimum Wicketkeepers | 1 |

**Note:** Role requirements and scoring rules are demonstration rules used in this project.

## 🧮 Fantasy Points Calculation

The project uses the following sample scoring system:

- 1 point for every run.
- 25 points for every wicket.
- 8 points for every catch.
- Captain receives 2x points.
- Vice-Captain receives 1.5x points.

### Formula

Fantasy Points = Runs + (Wickets × 25) + (Catches × 8)

## 📁 Project Structure

```text
FantasyXI/
│
├── Main.java
├── Player.java
├── PlayerManager.java
├── Team.java
├── FileManager.java
├── README.md
└── .gitignore
```

### Class Description

| Class | Responsibility |
|---|---|
| Main.java | Main menu and user interaction |
| Player.java | Stores player information |
| PlayerManager.java | Manages available players |
| Team.java | Team management, validation and points calculation |
| FileManager.java | Saves and loads team information |

## ⚙️ Requirements

- Java JDK 17 or above
- VS Code, IntelliJ IDEA or Eclipse
- Git (optional)

## 🚀 How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/nithin23maxy/FantasyX1-CP-082.git
```

### 2. Open the Project Folder

```bash
cd FantasyX1-CP-082
```

### 3. Compile the Java Files

```bash
javac Main.java Player.java PlayerManager.java Team.java FileManager.java
```

### 4. Run the Application

```bash
java Main
```

## 💾 File Handling

FantasyXI uses a text file named `fantasy_team.txt` to save and load selected team details.

The saved information includes:
- Player name
- Player role
- Real-world team
- Credits
- Captain or Vice-Captain status

The file is created automatically when the user saves a team.

## 📚 Java Concepts Demonstrated

- Classes and Objects
- Constructors
- Encapsulation
- ArrayList
- HashMap
- Methods
- Loops and Conditional Statements
- Switch Statements
- Exception Handling
- File Handling
- Sorting and Comparators

## 🔮 Future Enhancements

- Add more cricket players.
- Support multiple fantasy teams.
- Introduce match-wise leaderboards.
- Automate player statistics.
- Improve fantasy scoring options.

## 👨‍💻 Developer

**Nithin R.**

Information Science and Engineering

Atria Institute of Technology, Bangalore.

## 📜 Project Information

**Project:** FantasyXI - Fantasy Cricket Team Builder  
**Project Code:** CP-082  
**Language:** Java  
**Application Type:** Menu-Driven Console Application

---

⭐ If you find this project interesting, consider giving the repository a star!

