package br.fadep.pos.model.exeptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class RegraException extends IllegalArgumentException {

    private Integer codErro;
    private Object obj;

    public RegraException() {

    }

    public RegraException(Integer codErro, String msg) {
        super(msg);
        this.codErro = codErro;
    }

    public RegraException(Integer codErro, String msg, Object obj) {
        super(msg);
        this.codErro = codErro;
        this.obj = obj;
    }

    public RegraException(Integer codErro, String msg, Throwable throwable) {
        super(msg, throwable);
        this.codErro = codErro;
    }

    public RegraException(Integer codErro, String msg, Throwable throwable, Object obj) {
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
