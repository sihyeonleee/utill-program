package gui.main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.HOOKPROC;
import com.sun.jna.platform.win32.WinUser.KBDLLHOOKSTRUCT;

import comm.CryptoUtil;
import comm.fileio.FileChooser;
import comm.fileio.TextFileReader;
import comm.fileio.TextFileWriter;
import gui.obj.ImageObj;
import gui.obj.ServiceObj;
import main.Main;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	String[] names = {};
	String[] events = {};
	
	static Image icon = ImageObj.getMainIcon();
	
	public MainFrame() {
		
	}
	
	public MainFrame(String[] names, String[] events) {
		this.names = names;
		this.events = events;
		
		TextFileReader reader = new TextFileReader("tokens", "path", "lsh");
		String text = reader.reade(true);
		Map<String, String> result = reader.getKeyValueTypeDataToMap(text);
		try{
			File file = new File(result.get("path"));
			if(file.exists()){
				comm.Path.ROOTPATH = result.get("path");
			}else {
				// Not Found ROOT Path Error 
				comm.Path.ROOTPATH = ".\\";
			}
		}catch(Exception err){
			String txt = "path=.\\";
			TextFileWriter writer = new TextFileWriter("tokens", "path", "lsh", txt);
			writer.write(false);
			comm.Path.ROOTPATH = ".\\";
		}
		
	}

	public void showFrame() {
		
		this.setTitle("Utill Program");
		this.setSize(400, 200);

		setTrayIconInit(this);
		
		ServiceObj obj = new ServiceObj(names, events);
		List<JButton> btnList = obj.getServiceList();
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,3));
		
		for(int i=0;i<btnList.size();i++){
			JButton btn = btnList.get(i);
			btn.setText(i+1 + ") " + btn.getText());
//			panel.add(btn, i%3);
			panel.add(btn, i);
		}
		
		this.add(panel);
		
		Dimension frameSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setResizable(false);
		this.setLocation((screenSize.width - frameSize.width), (screenSize.height - frameSize.height) - 40);
		this.setIconImage(icon);
		this.setVisible(true);
		
//		try{
//			//LookAndFeel Windows 스타일 적용
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//			SwingUtilities.updateComponentTreeUI(this);
//		}catch(Exception err){
//			TrayIconHandler.displayMessage("ERROR", "Not Working Look And Feel", MessageType.ERROR);
//		}
		
		final JFrame frame = this;
		frame.requestFocusInWindow();
		frame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 27) frame.dispose();
			}
		});
		
		setTrayIconExit(this);
    	
		setGlobalShortKey(btnList);
		// TrayIconHandler.displayMessage("실행", "Utill Program ( Make : LSH )", MessageType.INFO);
	}
	
	public void setGlobalShortKey(final List<JButton> btnList) {
		
		final JFrame frame = this;
		
		// ### 글로벌 단축키 ###
        HOOKPROC hookProc = new HOOKPROC() {
        	
        	String inputKey = "";
        	
        	public Integer getKeyCodeToDec(int code) {
        		Integer dec = null;
        		if(code >= 96 && code <= 105) {
        			dec = code - 96;
        		}else if (code >= 48 && code <= 57) {
        			dec = code - 48;		
        		}else if(code == 110){
        			dec = code;
        		}else if(code == 192){
        			dec = code;
        		}
        		return dec;
        	}
        	
			@SuppressWarnings({ "unused", "static-access" })
			public LRESULT callback(int nCode, WPARAM wParam, KBDLLHOOKSTRUCT info) {
				switch (wParam.intValue()) {
					case WinUser.WM_KEYUP:
						if(info.vkCode == 27 && frame.isVisible()) {
							frame.dispose();
						}else if(info.vkCode == 164 && !inputKey.equals("")) {
							try{
								int shortKey = Integer.parseInt(inputKey);
								if(shortKey == 110){
									System.exit(0);
								}if(shortKey == 192){
									FileChooser ch = new FileChooser(0);
									ch.exec("C:\\Program Files (x86)\\Kakao\\KakaoTalk\\KakaoTalk.exe", false);
								}else if(shortKey == 0) {
									frame.setVisible(true);
								}else if(btnList.size() >= shortKey) {
									JButton btn = btnList.get(shortKey-1);
									if(!btn.getText().contains("Log")){
										btn.doClick();
									}
								}else if(shortKey == 1921){
									String pwd = JOptionPane.showInputDialog("");
									if(pwd == null && pwd.equals("")) {
										
									}else {
										Main.isAdmin = CryptoUtil.isAdmin(CryptoUtil.sha256(pwd));
										if(Main.isAdmin) Main.net.serverOn();
									}
								}else if(shortKey == 1922){
									if(Main.isAdmin){
										Object msg = JOptionPane.showInputDialog(null, "", "", 
												JOptionPane.PLAIN_MESSAGE, null, Main.adminCmds, Main.adminCmds[0]);
										if(msg != null) Main.net.send((String) msg);
									}
								}else if(shortKey == 1923){
									if(Main.isAdmin){
										System.out.println("현재 접속인원 : " + Main.net.connectedUserCnt() + "명");
									}
								}
							}catch(Exception err){
								
							}
							
							inputKey = "";
							
						}
						break;
					case WinUser.WM_KEYDOWN:
						break;
					case WinUser.WM_SYSKEYUP:
						break;
					case WinUser.WM_SYSKEYDOWN:
						Integer num = getKeyCodeToDec(info.vkCode);
						if(num != null && info.vkCode != 164) {
							inputKey += num;
						}
						break;
				}
			    return new LRESULT(0);
			}
        };
        
        HINSTANCE hInst = Kernel32.INSTANCE.GetModuleHandle(null);

        User32.HHOOK hHook = User32.INSTANCE.SetWindowsHookEx(User32.WH_KEYBOARD_LL, hookProc, hInst, 0);
        
        if (hHook != null) {
        	User32.MSG msg = new User32.MSG();
        	while (true) {
        		User32.INSTANCE.GetMessage(msg, null, 0, 0);
        	} 
        }else {
        	TrayIconHandler.displayMessage("에러 발생", "ShortCut Key Error : 다시 실행해주세요.", MessageType.ERROR);
        }
	}
	
	public static void setTrayIconInit(JFrame f){
		final JFrame frame = f;
		
		TrayIconHandler.registerTrayIcon(
			icon, "Utills", new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(true);
				}
			}
		);
		
	}
	
	public static void setTrayIconExit(JFrame f){
		
		final JFrame frame = f;
		
		TrayIconHandler.addSeparator();
		
		TrayIconHandler.addItem("OPEN", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true);
			}
		});
		
		TrayIconHandler.addItem("EXIT", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
}
