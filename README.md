# Algo-Coding-Ai
코딩풀이 게시물에 적용할 AI

# 파이썬 기반 FastAPI 설치

- Python 버전: **3.13**

---

## 📥 Python 설치

### Windows
[Python Releases for Windows](https://www.python.org/downloads/windows/)

### macOS
[Python Releases for macOS](https://www.python.org/downloads/macos/)

---

## ⚙️ 가상 환경 생성 및 활성화

```bash
# 가상 환경 생성
python -m venv venv

# Windows
venv\Scripts\activate

# FastAPI 및 Uvicorn 설치
pip install fastapi uvicorn

# python-dotenv 설치
pip install python-dotenv

# macOS/Linux
source venv/bin/activate

# 기본 실행
uvicorn main:app --reload

# 실행 후 확인 URL
# http://127.0.0.1:8000
# Swagger UI (API 문서): http://127.0.0.1:8000/docs
