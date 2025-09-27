package com.threego.algo.common.constants;

public class ValidationConstants {

    // 날짜 검증 정규식 (DateTimeUtils와 일치)
    public static final String DATE_PATTERN = "\\d{4}-\\d{2}-\\d{2}";
    public static final String DATE_TIME_PATTERN = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";

    // 날짜 검증 메시지
    public static final String DATE_FORMAT_MESSAGE = "날짜는 YYYY-MM-DD 형식이어야 합니다.";
    public static final String DATE_TIME_FORMAT_MESSAGE = "날짜시간은 YYYY-MM-DD HH:mm:ss 형식이어야 합니다.";

    private ValidationConstants() {
        // 유틸리티 클래스는 인스턴스화 방지
    }
}