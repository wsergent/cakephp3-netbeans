/*
 * Copyright 2019 junichi11.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.netbeans.modules.php.cake3.ui.options;

import java.beans.PropertyChangeEvent;
import java.util.List;
import javax.swing.DefaultListModel;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ui.OpenProjects;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.modules.php.cake3.modules.CakePHP3Module;
import org.netbeans.modules.php.cake3.options.CakePHP3Options;

final class CakePHP3OptionsPanel extends javax.swing.JPanel {

    private static final long serialVersionUID = -8545916715324471829L;

    private final CakePHP3OptionsPanelController controller;

    CakePHP3OptionsPanel(CakePHP3OptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        // TODO listen to changes in form fields and call controller.changed()
    }

    private void setExternalDragAndDrop(boolean isEnabled) {
        externalDragAndDropCheckBox.setSelected(isEnabled);
    }

    private boolean isExternalDragAndDrop() {
        return externalDragAndDropCheckBox.isSelected();
    }

    private void setAvailableCustomNodes() {
        CakePHP3Options options = CakePHP3Options.getInstance();
        DefaultListModel<String> defaultListModel = new DefaultListModel<>();
        for (String node : CakePHP3Options.ALL_AVAILABLE_NODES) {
            defaultListModel.addElement(node);
        }
        customNodesList.setModel(defaultListModel);
        List<String> availableCustomNodes = options.getAvailableCustomNodes();
        for (String node : availableCustomNodes) {
            int indexOf = defaultListModel.indexOf(node);
            customNodesList.addSelectionInterval(indexOf, indexOf);
        }
    }

    private void notifyPropertyChanged() {
        Project[] openProjects = OpenProjects.getDefault().getOpenProjects();
        for (Project project : openProjects) {
            PhpModule phpModule = PhpModule.Factory.lookupPhpModule(project);
            if (phpModule != null) {
                if (CakePHP3Module.isCakePHP(phpModule)) {
                    CakePHP3Module cakeModule = CakePHP3Module.forPhpModule(phpModule);
                    if (cakeModule == null) {
                        continue;
                    }
                    cakeModule.notifyPropertyChanged(new PropertyChangeEvent(this, CakePHP3Module.PROPERTY_CHANGE_CAKE3, null, null));
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        externalDragAndDropCheckBox = new javax.swing.JCheckBox();
        customNodesLabel = new javax.swing.JLabel();
        customNodesScrollPane1 = new javax.swing.JScrollPane();
        customNodesList = new javax.swing.JList<>();

        org.openide.awt.Mnemonics.setLocalizedText(externalDragAndDropCheckBox, org.openide.util.NbBundle.getMessage(CakePHP3OptionsPanel.class, "CakePHP3OptionsPanel.externalDragAndDropCheckBox.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(customNodesLabel, org.openide.util.NbBundle.getMessage(CakePHP3OptionsPanel.class, "CakePHP3OptionsPanel.customNodesLabel.text")); // NOI18N

        customNodesScrollPane1.setViewportView(customNodesList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(externalDragAndDropCheckBox)
                    .addComponent(customNodesLabel)
                    .addComponent(customNodesScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(externalDragAndDropCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(customNodesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customNodesScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    void load() {
        CakePHP3Options options = CakePHP3Options.getInstance();
        setExternalDragAndDrop(options.isExternalDragAndDrop());
        setAvailableCustomNodes();
    }

    void store() {
        CakePHP3Options options = CakePHP3Options.getInstance();
        options.setExternalDragAndDrop(isExternalDragAndDrop());
        List<String> nodes = customNodesList.getSelectedValuesList();
        options.setAvailableCustomNodes(nodes);
        // notify
        notifyPropertyChanged();
    }

    boolean valid() {
        // TODO check whether form is consistent and complete
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel customNodesLabel;
    private javax.swing.JList<String> customNodesList;
    private javax.swing.JScrollPane customNodesScrollPane1;
    private javax.swing.JCheckBox externalDragAndDropCheckBox;
    // End of variables declaration//GEN-END:variables
}
