package cn.jaminye.designpatterns.command.util;

import cn.jaminye.designpatterns.command.pojo.TextEditor;

/**
 * @author Jamin
 * @date 2021/3/17 9:38
 */
public class PrintCommand implements Command {
	TextEditor textEditor;

	public PrintCommand(TextEditor textEditor) {
		this.textEditor = textEditor;
	}

	@Override
	public void excute() {
		System.out.println(textEditor.getContent());
	}

	@Override
	public void excute(String content) {

	}
}
