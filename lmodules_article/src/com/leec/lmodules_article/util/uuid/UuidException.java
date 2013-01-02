package com.leec.lmodules_article.util.uuid;

/**
 * Created by IntelliJ IDEA.
 * Company (道一信息科技有限公司 DO1.ltd)
 * User: SARON
 * Date: 2007-6-12
 * Time: 16:35:29
 */
public class UuidException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UuidException() {
	super();
    }

    public UuidException(String msg) {
	super(msg);
    }
}
