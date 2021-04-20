package cn.jaminye.designpatterns.command.client;

import cn.jaminye.designpatterns.command.pojo.TextEditor;
import cn.jaminye.designpatterns.command.util.AddCommand;
import cn.jaminye.designpatterns.command.util.PrintCommand;

/**
 * 测试
 *
 * @author Jamin
 * @date 2021/3/17 9:40
 */

public class Test {
	public static void main(String[] args) {
		TextEditor textEditor = new TextEditor("ceshi");
		AddCommand addCommand = new AddCommand(textEditor);
		addCommand.excute("text");
		PrintCommand printCommand = new PrintCommand(textEditor);
		printCommand.excute();
	}
}
