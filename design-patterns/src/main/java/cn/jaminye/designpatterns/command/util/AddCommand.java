package cn.jaminye.designpatterns.command.util;

import cn.jaminye.designpatterns.command.pojo.TextEditor;

/**
 * @author Jamin
 * @date 2021/3/17 9:30
 */
public class AddCommand implements Command {
	TextEditor textEditor;

	public AddCommand(TextEditor textEditor) {
		this.textEditor = textEditor;
	}

	@Override
	public void excute() {

	}

	@Override
	public void excute(String content) {
		this.textEditor.setContent(this.textEditor.getContent() + content);
	}
}
