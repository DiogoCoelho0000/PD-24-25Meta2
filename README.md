# 💰 Gestão de Compras (Splitwise-inspired)

Distributed Java system for splitting shared expenses (Splitwise-inspired). Started with direct socket/multicast communication, then evolved to a REST API (Spring Boot, JWT) plus an RMI service using the Observer pattern for real-time notifications.

## ✨ Features

- User registration, login, and group/expense management (list, add, delete)
- Real-time notifications when clients request data, via an Observer pattern over RMI

## 🛠️ Architecture

- **REST API** built with Spring Boot, secured with **JWT** — all endpoints (except register/login) require a valid token (10-minute expiry)
- **RMI service** for asynchronous notifications and remote access to system data, using the **Observer pattern** to notify registered listeners of real-time events
- Two dedicated clients: a **REST client** for everyday operations (register, list/add/delete expenses) and an **RMI client** for listing connected users/groups
- SQL-backed persistence for users, groups, and expenses

## 🚀 Running locally

```bash
git clone https://github.com/DiogoCoelho0000/PD-24-25Meta2-GestaoCompras-Splitwise.git
cd PD-24-25Meta2-GestaoCompras-Splitwise
# start the Spring Boot server, then run the REST and/or RMI clients
```

> Requires Java 17+ and a local SQL database instance.

<img width="344" height="270" alt="image" src="https://github.com/user-attachments/assets/50e66cc3-a151-4c8b-9274-978e4365aa9c" />
