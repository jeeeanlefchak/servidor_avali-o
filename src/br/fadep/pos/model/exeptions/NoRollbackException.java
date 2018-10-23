package br.fadep.pos.model.exeptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = false)
public class NoRollbackException extends Exception {

    private Integer codErro;
    private Object obj;

    public NoRollbackException() {

    }

    public NoRollbackException(Integer codErro, String msg) {
        super(msg);
        this.codErro = codErro;
    }

    public NoRollbackException(Integer codErro, String msg, Object obj) {
        super(msg);
        this.codErro = codErro;
        this.obj = obj;
    }

    public NoRollbackException(Integer codErro, String msg, Throwable throwable) {
        super(msg, throwable);
        this.codErro = codErro;
    }

    public NoRollbackException(Integer codErro, String msg, Throwable throwable, Object obj) {
        super(msg, throwable);
        this.codErro = codErro;
        this.obj = obj;
    }

    public Integer getCodErro() {
        return codErro;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

}
