package com.github.kongpf8848.logviewer;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class LogViewerToolWindow implements ToolWindowFactory, LogPrinter {

    private JPanel toolWindowContent;
    private JButton startServerButton;
    private JTextArea logTextArea;
    private JScrollPane scrollview;

    public LogViewerToolWindow(){
        startServerButton.addActionListener(e -> {
            new Thread(() -> LogServer.startServer(LogViewerToolWindow.this)).start();
        });
    }
    private void createUIComponents() {

    }

    @Override
    public void log(String msg) {
        SwingUtilities.invokeLater(() -> {
            logTextArea.append(msg);
            logTextArea.append("\n");
            logTextArea.setCaretPosition(logTextArea.getDocument().getLength());
            logTextArea.paintImmediately(logTextArea.getBounds());
        });

    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(toolWindowContent, "", false);
        toolWindow.getContentManager().addContent(content);
    }



}
