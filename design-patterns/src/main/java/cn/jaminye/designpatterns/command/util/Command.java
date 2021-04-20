package cn.jaminye.designpatterns.command.util;

/**
 * @author Jamin
 * @date 2021/3/17 9:29
 */
public interface Command {
	void excute();

	void excute(String content);
}
