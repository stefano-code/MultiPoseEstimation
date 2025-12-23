# Android Multi-Person Pose Estimation with MoveNet

This project is a simple Android application that performs **real-time multi-person pose estimation** using **MoveNet MultiPose** and **TensorFlow Lite**, running completely **on-device**.

The app uses the phone camera to detect multiple people in the scene and draws a **skeleton overlay** on top of each body, even during fast movements such as **fitness or dance workouts (e.g. Zumba)** 💃🕺.

The main goal of this project is to show a **clean and understandable end-to-end pipeline**:
- Camera capture using the Android Camera2 API 📷
- Real-time pose estimation with MoveNet MultiPose 🦴
- Optional multi-person tracking across frames 🔄
- Skeleton visualization directly on the camera preview 🎨

This repository is meant to be **educational**, with readable code and minimal abstractions.

---

## ✨ Features

- Real-time **multi-person pose estimation**
- Fully **on-device** inference (no network required)
- Smooth skeleton drawing on moving people
- Optional tracking using:
  - Bounding boxes
  - Keypoint similarity (OKS-based)
- Designed to run efficiently on Android devices

---

## 🧠 Model

- **MoveNet MultiPose**
- TensorFlow Lite (`movenet_multipose_fp16.tflite`)
- 17 body keypoints per person (COCO format)

---

## 🛠️ Tech Stack

- Kotlin
- Android Camera2 API
- TensorFlow Lite
- MoveNet MultiPose
- On-device rendering with Canvas

---

## 📷 Screenshot / Demo

<img width="1440" height="3120" alt="Screenshot_20251223_201735" src="https://github.com/user-attachments/assets/d08e422f-0782-4f1f-b7f7-b1b80e9e919d" />
