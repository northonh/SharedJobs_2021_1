package br.edu.ifsp.scl.ads.pdm.sharedjobs;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.ifsp.scl.ads.pdm.sharedjobs.databinding.ActivityMainBinding;

import static br.edu.ifsp.scl.ads.pdm.sharedjobs.R.id.limpartBt;
import static br.edu.ifsp.scl.ads.pdm.sharedjobs.R.id.salvarBt;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        activityMainBinding.adicionarCelularSw.setOnCheckedChangeListener( (buttonView,  isChecked) -> {
            if (isChecked) {
                activityMainBinding.celularEt.setVisibility(View.VISIBLE);
            }
            else {
                activityMainBinding.celularEt.setVisibility(View.GONE);
                activityMainBinding.celularEt.setText("");
            }
        });

        activityMainBinding.formacaoSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String formacao = ((TextView) view).getText().toString();
                switch (formacao) {
                    case "Fundamental":
                    case "Médio":
                        activityMainBinding.tituloMonografiaEt.setVisibility(View.GONE);
                        activityMainBinding.orientadorEt.setVisibility(View.GONE);
                        activityMainBinding.instituicaoEt.setVisibility(View.GONE);
                        activityMainBinding.tituloMonografiaEt.setText("");
                        activityMainBinding.orientadorEt.setText("");
                        activityMainBinding.instituicaoEt.setText("");
                        break;
                    case "Graduação":
                    case "Especialização":
                        activityMainBinding.tituloMonografiaEt.setVisibility(View.GONE);
                        activityMainBinding.orientadorEt.setVisibility(View.GONE);
                        activityMainBinding.instituicaoEt.setVisibility(View.VISIBLE);
                        activityMainBinding.tituloMonografiaEt.setText("");
                        activityMainBinding.orientadorEt.setText("");
                        break;
                    case "Mestrado":
                    case "Doutorado":
                        activityMainBinding.tituloMonografiaEt.setVisibility(View.VISIBLE);
                        activityMainBinding.orientadorEt.setVisibility(View.VISIBLE);
                        activityMainBinding.instituicaoEt.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Não é necessário
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()){
            case salvarBt:
                saveForm();
                break;
            case limpartBt:
                cleanForm();
                break;
            default:
                break;
        }
    }

    private void cleanForm() {
        activityMainBinding.nomeEt.setText("");
        activityMainBinding.emailEt.setText("");
        activityMainBinding.receberEmailsCb.setChecked(false);
        activityMainBinding.telefoneEt.setText("");
        activityMainBinding.tipoTelefoneRg.check(R.id.residencialRb);
        activityMainBinding.adicionarCelularSw.setChecked(false);
        activityMainBinding.celularEt.setText("");
        activityMainBinding.sexoRg.check(R.id.masculinoRb);
        activityMainBinding.dataNascimentoEt.setText("");
        activityMainBinding.formacaoSp.setSelection(0);
        activityMainBinding.anoConclusaoEt.setText("");
        activityMainBinding.instituicaoEt.setText("");
        activityMainBinding.tituloMonografiaEt.setText("");
        activityMainBinding.orientadorEt.setText("");
        activityMainBinding.vagasInteresseEt.setText("");
    }

    private void saveForm() {
        StringBuilder formSummary = new StringBuilder();
        formSummary.append("Nome: ").append(activityMainBinding.nomeEt.getText().toString()).append("\n")
                .append("E-mail: ").append(activityMainBinding.emailEt.getText().toString()).append("\n")
                .append("Receber oportunidades? ").append((activityMainBinding.receberEmailsCb.isChecked())?"Sim":"Não").append("\n")
                .append("Telefone ").append(activityMainBinding.residencialRb.isChecked()?"residencial: ":"comercial: ")
                .append(activityMainBinding.telefoneEt.getText().toString()).append("\n");
        if (activityMainBinding.adicionarCelularSw.isChecked()) {
            formSummary.append("Telefone celular: ").append(activityMainBinding.celularEt.getText().toString()).append("\n");
        }
        formSummary.append("Sexo: ").append(activityMainBinding.masculinoRb.isChecked()?"Masculino":"Feminino").append("\n")
                .append("Data de nascimento: ").append(activityMainBinding.dataNascimentoEt.getText().toString()).append("\n");
        String formacao = ((TextView) activityMainBinding.formacaoSp.getSelectedView()).getText().toString();
        formSummary.append("Formação: ").append(formacao).append("\n")
                .append("Ano de conclusão: ").append(activityMainBinding.anoConclusaoEt.getText().toString()).append("\n");
        String instituicao = activityMainBinding.instituicaoEt.getText().toString();
        if (!instituicao.isEmpty()) {
            formSummary.append("Instituição: ").append(instituicao).append("\n");
        }
        String tituloMonografia = activityMainBinding.tituloMonografiaEt.getText().toString();
        if (!tituloMonografia.isEmpty()){
            formSummary.append("Título da monografia: ").append(tituloMonografia).append("\n")
            .append("Orientador: ").append(activityMainBinding.orientadorEt.getText().toString());
        }
        formSummary.append("Vagas de interesse: ").append(activityMainBinding.vagasInteresseEt.getText().toString());
        Toast.makeText(this, formSummary.toString(), Toast.LENGTH_SHORT).show();
    }
}