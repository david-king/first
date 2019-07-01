package com.durian.first.core.exception;

import com.durian.first.core.base.BaseEnum;
import com.durian.first.core.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     *   启动应用后，被 @ExceptionHandler、@InitBinder、@ModelAttribute 注解的方法，
     *   都会作用在 被 @RequestMapping 注解的方法上。
     * @param binder 参数
     */
    @InitBinder
    public void initWebBinder(WebDataBinder binder){

    }

    /**
     * 系统错误，未知的错误   已测试
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler({Exception.class})
    public Result exception(Exception ex){
        log.error("错误详情：" + ex.getMessage(),ex);
        return Result.errorJson(BaseEnum.SYSTEM_ERROR.getMsg(),BaseEnum.SYSTEM_ERROR.getIndex());
    }

    /**
     * 文件没有找到错误拦截   **要把错误信息抛出到controller层  已测试
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(FileNotFoundException.class)
    public Result fileNotFound(FileNotFoundException ex){
        log.error("错误详情：" + ex.getMessage(),ex);
        return Result.errorJson(BaseEnum.FILE_NOT_FOUND.getMsg(),BaseEnum.FILE_NOT_FOUND.getIndex());
    }

    /**
     * 字符串转换为数字异常   已测试  不需要抛出到ccontroller即可被拦截
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(NumberFormatException.class)
    public Result numberFormatEx(NumberFormatException ex){
        log.error("错误详情：" + ex.getMessage(),ex);
        return Result.errorJson(BaseEnum.NUMBER_FORMAT.getMsg(),BaseEnum.NUMBER_FORMAT.getIndex());
    }

    /**
     * sql操作数据库出错了
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(SQLException.class)
    public Result sqlException(SQLException ex){
        log.error("错误详情：" + ex.getMessage(),ex);
        return Result.errorJson(BaseEnum.SQL_EXCEPTION.getMsg(),BaseEnum.SQL_EXCEPTION.getIndex());
    }

    /**
     * 参数传递出错了
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result sqlException(IllegalArgumentException ex){
        log.error("错误详情：" + ex.getMessage(),ex);
        return Result.errorJson(BaseEnum.ILLEGAL_ARGUMENT.getMsg(),BaseEnum.ILLEGAL_ARGUMENT.getIndex());
    }

    /**
     * 栈溢出错误
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(StackOverflowError.class)
    public Result stackOverflow(StackOverflowError ex){
        log.error("错误详情：" + ex.getMessage(),ex);
        return Result.errorJson(BaseEnum.STACK_OVERFLOW.getMsg(),BaseEnum.STACK_OVERFLOW.getIndex());
    }

    /**
     * 404错误拦截   已测试
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result noHandlerNotFound(NoHandlerFoundException ex){
        log.error("错误详情：" + ex.getMessage(),ex);
        return Result.errorJson(BaseEnum.NO_HANDLER.getMsg(),BaseEnum.NO_HANDLER.getIndex());
    }

    /**
     * 400错误拦截
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(TypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result request400(TypeMismatchException ex){
        log.error("错误详情：" + ex.getMessage(),ex);
        return Result.errorJson(BaseEnum.BAD_REQUEST.getMsg(),BaseEnum.BAD_REQUEST.getIndex());
    }

    /**
     * 400错误拦截
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result request400(MissingServletRequestParameterException ex){
        log.error("错误详情：" + ex.getMessage(),ex);
        return Result.errorJson(BaseEnum.BAD_REQUEST.getMsg() + "   找不到传入的参数",BaseEnum.BAD_REQUEST.getIndex());
    }

    /**
     * 400错误拦截
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result request400(HttpMessageNotReadableException ex){
        log.error("错误详情：" + ex.getMessage(),ex);
        return Result.errorJson(BaseEnum.BAD_REQUEST.getMsg() + "    可能缺少参数",BaseEnum.BAD_REQUEST.getIndex());
    }

    /**
     * 405错误拦截
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Result request405(HttpRequestMethodNotSupportedException ex){
        log.error("错误详情：" + ex.getMessage(),ex);
        return Result.errorJson(BaseEnum.METHOD_NOT_ALLOWED.getMsg(),BaseEnum.METHOD_NOT_ALLOWED.getIndex());
    }

    /**
     * 500错误拦截
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result request500(RuntimeException ex){
        log.error("错误详情：" + ex.getMessage(),ex);
        return Result.errorJson(BaseEnum.INTERNAL_SERVER_ERROR.getMsg(),BaseEnum.INTERNAL_SERVER_ERROR.getIndex());
    }

    /**
     * 类型转换异常   已经测试 可以拦截
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(ClassCastException.class)
    public Result classCastExceptionHandler(ClassCastException ex) {
        log.error("错误详情：" + ex.getMessage(),ex);
        return Result.errorJson(BaseEnum.CLASS_CAST.getMsg(),BaseEnum.CLASS_CAST.getIndex());
    }

    /**
     * 未知方法异常
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(NoSuchMethodException.class)
    public Result noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        log.error("错误详情：" + ex.getMessage(),ex);
        return Result.errorJson(BaseEnum.NO_SUCH_METHOD.getMsg(),BaseEnum.NO_SUCH_METHOD.getIndex());
    }

    /**
     * IO异常 需要抛出到Controller层可捕获到
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(IOException.class)
    public Result iOExceptionHandler(IOException ex) {
        log.error("错误详情：" + ex.getMessage(),ex);
        return Result.errorJson(BaseEnum.IO_EXCEPTION.getMsg(),BaseEnum.IO_EXCEPTION.getIndex());
    }

    /**
     * 空指针异常  已测试  可以拦截
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(NullPointerException.class)
    public Result nullPointerExceptionHandler(NullPointerException ex) {
        log.error("错误详情：" + ex.getMessage(),ex);
        return Result.errorJson(BaseEnum.NULL_POINTER.getMsg(), BaseEnum.NULL_POINTER.getIndex());
    }

    /**
     * 数组越界异常拦截   已测试
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public Result indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex){
        log.warn("错误详情：" + ex.getMessage(),ex);
        return Result.errorJson(BaseEnum.INDEX_OUT_BOUNDS.getMsg(),BaseEnum.INDEX_OUT_BOUNDS.getIndex());
    }

    /**
     * 自定义异常信息拦截
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(MyException.class)
    public Result myCustomizeException(MyException ex){
        log.warn("错误详情：" + ex);
        return Result.errorJson(BaseEnum.CUSTOMIZE_EXCEPTION.getMsg(),BaseEnum.CUSTOMIZE_EXCEPTION.getIndex());
    }

}
