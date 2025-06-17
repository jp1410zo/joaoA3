/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package a3conexao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author noobg
 */
public class TelaSapatariaCRUD extends JFrame {

    private final DefaultTableModel tableModel
            = new DefaultTableModel(new Object[]{"Nome", "Telefone", "Tipo de calçado", "Tipo de conserto"}, 0);
    private final JTable tblSapataria = new JTable(tableModel);

    private final JTextField txtNome = new JTextField(20);
    private final JTextField txtTelefone = new JTextField(20);
    private final JTextField txtTipocalçado = new JTextField(10);
    private final JTextField txtTipodeconserto = new JTextField(10);

    private final JButton btnNovo = new JButton("Novo");
    private final JButton btnSalvar = new JButton("Salvar");
    private final JButton btnEditar = new JButton("Editar");
    private final JButton btnExcluir = new JButton("Excluir");
    private final JButton btnRefresh = new JButton("Atualizar");

    private final SapatariaDAO dao = new SapatariaDAO();

    public TelaSapatariaCRUD() {
        super("Sapataria");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);

        initLayout();
        registerListeners();
        carregarTabela();

        setVisible(true);
    }

    private void initLayout() {
        JPanel form = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        form.add(new JLabel("Nome:"));
        txtNome.setDocument(new JTextFieldLimit(200));
        form.add(txtNome);
        form.add(new JLabel("Telefone:"));
        form.add(txtTelefone);
        form.add(new JLabel("Tipo de calçado:"));
        form.add(txtTipocalçado);
        form.add(new JLabel("Tipo de conserto:"));
        form.add(txtTipodeconserto);
        form.add(btnNovo);
        form.add(btnEditar);
        form.add(btnExcluir);
        form.add(btnRefresh);

        JScrollPane scroll = new JScrollPane(tblSapataria);

        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().add(form, BorderLayout.CENTER);
        getContentPane().add(scroll, BorderLayout.SOUTH);

    }

    private void limparCampos() {
        txtNome.setText("");
        txtTelefone.setText("");
        txtTipocalçado.setText("");
        txtTipodeconserto.setText("");
        tblSapataria.clearSelection();

    }

    private void carregarTabela() {
        try {
            List<Sapataria> Lista = dao.findAll();
            tableModel.setRowCount(0);
            for (Sapataria e : Lista) {
                tableModel.addRow(new Object[]{e.getNome(), e.getTelefone(), e.getTipocalçado(), e.getTipodeconserto()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao carregar tabela: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registerListeners() {
        btnRefresh.addActionListener(e -> carregarTabela());

        btnNovo.addActionListener(e -> {
            txtNome.setText("");
            txtTelefone.setText("");
            txtTipocalçado.setText("");
            txtTipodeconserto.setText("");

            tblSapataria.clearSelection();
            txtNome.requestFocusInWindow();
        });
        btnSalvar.addActionListener(e -> {
            try {
                String nome = txtNome.getText().trim();
                String telefone = txtTelefone.getText().trim();
                String tipocalçado = txtTipocalçado.getText().trim();
                String tipodeconserto = txtTipodeconserto.getText().trim();
                if (nome.length() <= 20 || nome.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Coloque nome e sobrenome e preencha as outras colunas",
                            "Validação", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Sapataria exist = getSelectedSapataria();
                if (exist == null) {
                    dao.save(new Sapataria(nome.toUpperCase(), telefone, tipocalçado, tipodeconserto));
                } else {
                    exist.setNome(nome);
                    dao.update(exist);
                }
                carregarTabela();
                limparCampos();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                        "Erro ao salvar: " + ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnEditar.addActionListener(e -> {
            Sapataria sel = getSelectedSapataria();
            if (sel != null) {
                txtNome.setText(sel.getNome());
                txtTelefone.setText(sel.getTelefone());
                txtTipocalçado.setText(sel.getTipocalçado());
                txtTipodeconserto.setText(sel.getTipodeconserto());
            }
        });
        btnExcluir.addActionListener(e -> {
            Sapataria sel = getSelectedSapataria();
            if (sel != null
                    && JOptionPane.showConfirmDialog(this,
                            "Excluir nome " + sel.getNome() + "?",
                            "Confirma", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    dao.delete(sel.getNome());
                    carregarTabela();
                    limparCampos();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Erro ao excluir: " + ex.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private Sapataria getSelectedSapataria() {
        int row = tblSapataria.getSelectedRow();
        if (row < 0) {
            return null;
        }
        return new Sapataria(
                (String) tableModel.getValueAt(row, 0),
                (String) tableModel.getValueAt(row, 1),
                (String) tableModel.getValueAt(row, 2),
                (String) tableModel.getValueAt(row, 3)
        );
    }

    static class JTextFieldLimit extends javax.swing.text.PlainDocument {

        private final int limit;

        JTextFieldLimit(int limit) {
            this.limit = limit;
        }

        @Override
        public void insertString(int offs, String str,
                javax.swing.text.AttributeSet a)
                throws javax.swing.text.BadLocationException {
            if (str == null || getLength() + str.length() > limit) {
                return;
            }
            super.insertString(offs, str.toUpperCase(), a);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaSapatariaCRUD::new);
    }
}
