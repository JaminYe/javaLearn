package cn.jaminye.designpatterns.command.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jamin
 * @date 2021/3/17 9:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextEditor {
	/**
	 * 内容
	 */
	private String content;

}
