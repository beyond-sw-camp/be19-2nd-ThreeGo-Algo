from pydantic import BaseModel
from typing import List, Dict

class FeedbackRequest(BaseModel):
    title: str
    content: str
    problem: str

class FeedbackResponse(BaseModel):
    aiBigO: str
    aiGood: str
    aiBad: str
    aiPlan: str
