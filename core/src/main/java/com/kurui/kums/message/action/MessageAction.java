package com.kurui.kums.message.action;

import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.kurui.kums.base.exception.AppException;
import com.kurui.kums.base.external.http.server.ServletUtil;
import com.kurui.kums.base.struts.BaseAction;
import com.kurui.kums.base.ui.inform.Inform;
import com.kurui.kums.message.Message;
import com.kurui.kums.message.biz.MessageBiz;
import com.kurui.kums.message.jms.jboss.queues.HelloQueue;
import com.kurui.kums.message.jms.jboss.topics.HelloPublisher;

public class MessageAction extends BaseAction {
	private MessageBiz messageBiz;

	public ActionForward executeBaseCase(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws AppException {
		String forwardPage = "";
		Inform inf = new Inform();
		try {
			Message message = (Message) form;
			String method = message.getMethod();
			String providerIp = message.getProviderIp();
			ServletUtil.printResult("executeBaseCase,method:" + method,
					response);

			String messageText = message.getMessageText();
			try {
				try {
					String JNDIName = "topic/testTopic";
					if ("topicPublisher".equals(method)) {
						HelloPublisher topicPublisher = new HelloPublisher(
								providerIp, "ConnectionFactory", JNDIName);
						topicPublisher.publish(messageText);
						inf.setMessage("向" + JNDIName + "发送消息成功");
					}

					JNDIName = "queue/kuruiQueue";
					if ("queuePublisher".equals(method)) {
						HelloQueue queueBean = new HelloQueue(
								"ConnectionFactory", JNDIName);
						queueBean.send(messageText);
						inf.setMessage("向" + JNDIName + "发送消息成功");
					}
				} catch (JMSException e) {
					e.printStackTrace();
					inf.setMessage("异常信息1：" + e.getMessage());
				}
			} catch (NamingException e) {
				e.printStackTrace();
				inf.setMessage("异常信息2：" + e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			inf.setMessage("异常信息3：" + e.getMessage());
		}
		return forwardInformPage(inf, mapping, request);
	}

	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws AppException {
		String forwardPage = "";
		try {
			ServletUtil.printResult("---Kums Client Message.do=====", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward(forwardPage);
	}

	public void setMessageBiz(MessageBiz messageBiz) {
		this.messageBiz = messageBiz;
	}

}