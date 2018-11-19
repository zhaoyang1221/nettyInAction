package nia.echoclient;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

import static io.netty.channel.ChannelHandler.*;

/**
 * <p>Title: EchoClientHandler</p>
 *
 * @description:
 * @author: xiongzy
 * @date: 2018-11-19
 **/
@Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

	/**
	 * 当被通知channel是活跃的时候，发送一条消息
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
		System.out.println("Client received: " + msg.toString(CharsetUtil.UTF_8));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}