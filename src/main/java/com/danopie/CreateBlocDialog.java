package com.danopie;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class CreateBlocDialog extends DialogWrapper {
    private final Listener listener;
    private JPanel panel1;
    private JTextField nameTextField;

    CreateBlocDialog(final Listener listener){
        super(null);
        init();
        this.listener = listener;
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return panel1;
    }

    @Override
    protected void doOKAction() {
        super.doOKAction();
        final String text = nameTextField.getText();
        if(text == null || text.isEmpty()) return;

        listener.onCreateClicked(text);
    }

    @Nullable
    @Override
    public JComponent getPreferredFocusedComponent() {
        return nameTextField;
    }

}
