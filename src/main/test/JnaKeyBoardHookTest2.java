package main.test;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.HOOKPROC;
import com.sun.jna.platform.win32.WinUser.KBDLLHOOKSTRUCT;

public class JnaKeyBoardHookTest2 {


    public static void main(String[] args) throws Exception {
        HOOKPROC hookProc = new HOOKPROC() {
			@SuppressWarnings("unused")
			public LRESULT callback(int nCode, WPARAM wParam, KBDLLHOOKSTRUCT info) {
				System.err.println("callback bbbnhkilhjkibh nCode: " + info.vkCode);
			    return new LRESULT(0);
			}
        };
        HINSTANCE hInst = Kernel32.INSTANCE.GetModuleHandle(null);

        User32.HHOOK hHook = User32.INSTANCE.SetWindowsHookEx(User32.WH_KEYBOARD_LL, hookProc, hInst, 0);
        if (hHook == null)
            return;
        User32.MSG msg = new User32.MSG();
        System.err.println("Please press any key ....");
        while (true) {
            User32.INSTANCE.GetMessage(msg, null, 0, 0);
        }   
    }
}

class HOOKPROC_bg implements HOOKPROC {

    public HOOKPROC_bg() {
    }

    public LRESULT callback(int nCode, WPARAM wParam, KBDLLHOOKSTRUCT info) {
        System.err.println("callback bbbnhkilhjkibh nCode: " + info.vkCode);
        return new LRESULT(0);
    }
}