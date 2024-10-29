/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.register;

import java.io.Serializable;

/**
 *
 * @author PHUCHAU
 */
public class RegisterCreateError implements Serializable{
    private String usenameIsExisted;
    private String usenameLengthErr;
    private String passwordLengthErr;
    private String confirmNotMatch;
    private String fullnameLengthErr;

    public RegisterCreateError() {
    }

    
    
    /**
     * @return the usenameIsExisted
     */
    public String getUsenameIsExisted() {
        return usenameIsExisted;
    }

    /**
     * @param usenameIsExisted the usenameIsExisted to set
     */
    public void setUsenameIsExisted(String usenameIsExisted) {
        this.usenameIsExisted = usenameIsExisted;
    }

    /**
     * @return the usenameLengthErr
     */
    public String getUsenameLengthErr() {
        return usenameLengthErr;
    }

    /**
     * @param usenameLengthErr the usenameLengthErr to set
     */
    public void setUsenameLengthErr(String usenameLengthErr) {
        this.usenameLengthErr = usenameLengthErr;
    }

    /**
     * @return the passwordLengthErr
     */
    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    /**
     * @param passwordLengthErr the passwordLengthErr to set
     */
    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    /**
     * @return the confirmNotMatch
     */
    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    /**
     * @param confirmNotMatch the confirmNotMatch to set
     */
    public void setConfirmNotMatch(String confirmNotMatch) {
        this.confirmNotMatch = confirmNotMatch;
    }

    /**
     * @return the fullnameLengthErr
     */
    public String getFullnameLengthErr() {
        return fullnameLengthErr;
    }

    /**
     * @param fullnameLengthErr the fullnameLengthErr to set
     */
    public void setFullnameLengthErr(String fullnameLengthErr) {
        this.fullnameLengthErr = fullnameLengthErr;
    }
    
}
