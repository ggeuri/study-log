package model;

/**
 * StudentValidator 클래스
 * 
 * 학생 정보의 유효성을 검증합니다.
 * 이름, 나이, 점수의 유효성을 확인합니다.
 * 
 * SRP 원칙: 데이터 검증만 담당
 * 
 * @author 학생관리프로그램
 * @version 2.0
 */
public class StudentValidator {
    
    // ==================== 검증 상수 ====================
    
    private static final int MIN_AGE = 0;
    private static final int MAX_AGE = 150;
    private static final double MIN_SCORE = 0.0;
    private static final double MAX_SCORE = 100.0;
    
    // ==================== 검증 메서드 ====================
    
    /**
     * 이름의 유효성을 검증합니다.
     * 
     * @param name 검증할 이름
     * @return 유효하면 true
     */
    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty() && name.length() <= 50;
    }
    
    /**
     * 이름 유효성 검증 오류 메시지를 반환합니다.
     * 
     * @param name 검증할 이름
     * @return 오류 메시지, 유효하면 null
     */
    public static String getNameErrorMessage(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "이름은 비워두실 수 없습니다.";
        }
        if (name.length() > 50) {
            return "이름은 50자 이하여야 합니다.";
        }
        return null;
    }
    
    /**
     * 나이의 유효성을 검증합니다.
     * 
     * @param age 검증할 나이
     * @return 유효하면 true
     */
    public static boolean isValidAge(int age) {
        return age >= MIN_AGE && age <= MAX_AGE;
    }
    
    /**
     * 나이 유효성 검증 오류 메시지를 반환합니다.
     * 
     * @param ageStr 검증할 나이 문자열
     * @return 오류 메시지, 유효하면 null
     */
    public static String getAgeErrorMessage(String ageStr) {
        try {
            int age = Integer.parseInt(ageStr.trim());
            if (age < MIN_AGE || age > MAX_AGE) {
                return "나이는 " + MIN_AGE + "~" + MAX_AGE + " 범위여야 합니다.";
            }
            return null;
        } catch (NumberFormatException e) {
            return "나이는 숫자여야 합니다.";
        }
    }
    
    /**
     * 점수의 유효성을 검증합니다.
     * 
     * @param score 검증할 점수
     * @return 유효하면 true
     */
    public static boolean isValidScore(double score) {
        return score >= MIN_SCORE && score <= MAX_SCORE;
    }
    
    /**
     * 점수 유효성 검증 오류 메시지를 반환합니다.
     * 
     * @param scoreStr 검증할 점수 문자열
     * @return 오류 메시지, 유효하면 null
     */
    public static String getScoreErrorMessage(String scoreStr) {
        try {
            double score = Double.parseDouble(scoreStr.trim());
            if (score < MIN_SCORE || score > MAX_SCORE) {
                return "점수는 " + MIN_SCORE + "~" + MAX_SCORE + " 범위여야 합니다.";
            }
            return null;
        } catch (NumberFormatException e) {
            return "점수는 숫자여야 합니다.";
        }
    }
    
    /**
     * 학생 정보 전체를 검증합니다.
     * 
     * @param name 이름
     * @param ageStr 나이 문자열
     * @param scoreStr 점수 문자열
     * @return 모두 유효하면 null, 오류 메시지 반환
     */
    public static String validateStudent(String name, String ageStr, String scoreStr) {
        String nameError = getNameErrorMessage(name);
        if (nameError != null) {
            return nameError;
        }
        
        String ageError = getAgeErrorMessage(ageStr);
        if (ageError != null) {
            return ageError;
        }
        
        String scoreError = getScoreErrorMessage(scoreStr);
        if (scoreError != null) {
            return scoreError;
        }
        
        return null;
    }
}
