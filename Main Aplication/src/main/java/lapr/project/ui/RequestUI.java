package lapr.project.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.ApplicationGPSD;
import lapr.project.gpsd.controller.RequestController;
import lapr.project.gpsd.model.Category;
import lapr.project.gpsd.model.Client;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.PostalAddress;
import lapr.project.gpsd.model.RegisterCategory;
import lapr.project.gpsd.model.RegisterRequest;
import lapr.project.gpsd.model.RegisterService;
import lapr.project.gpsd.model.Service;
import lapr.project.gpsd.model.Time;

/**
 *
 * @author rickropes
 */
public class RequestUI implements Initializable {

    private ClientUI cControler;
    Client cliente;
    ApplicationGPSD app;
    Company empresa;

    PostalAddress pedidoMorada;
    String pedidoCategoria;
    Service pedidoServico;
    String pedidoData;
    int pedidoHoras;
    int pedidoMinutos;
    double priceTotal;
    double priceAdd;
    double precoAdicional = -1;
    String pedidoAdicional;
    private Stage novoPedidoStage;
    List<String> horarios = new ArrayList<String>();
    PostalAddress adicionarEndereco = new PostalAddress("Adicionar Novo Endereco", " ", " ");
    List<Service> listaServicos;
    List<Time> listExecTime;
    int Fminuto;
    int Fhora;
    boolean changed;
    private static final RequestController pc = new RequestController();

    @FXML
    public ComboBox<PostalAddress> butaoMoradas;
    @FXML
    private ComboBox<String> butaoCategorias;
    @FXML
    private ComboBox<Service> butaoServico;
    @FXML
    private Text descCompleta;
    @FXML
    private TextArea butaoTexto;
    @FXML
    private Text butaoPreco;
    @FXML
    private Button butaoConfirmar;
    @FXML
    private Button butaoHorarios;
    @FXML
    private TextField fieldHoras;
    @FXML
    private Button butaoUp;
    @FXML
    private Button butaoDown;
    @FXML
    private Button butAdicionarS;
    @FXML
    private Text textAdd;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        criarJanelaPedido();
        butaoUp.setDisable(true);
        butaoDown.setDisable(true);
        butaoPreco.setText("0€");
        textAdd.setText("");
        listaServicos = new ArrayList<Service>();
        listExecTime = new ArrayList<Time>();

    }

    /**
     * Permite adicionar um novo Endereço Postal ao Cliente com sessão efetuada na aplicação
     *
     * @param event
     */
    @FXML
    private void clicarMorada(ActionEvent event) {
        if (butaoMoradas.getValue() == adicionarEndereco) {

            changed = false;
            abrirJanelaEndereco();
            if (changed) {
                butaoMoradas.getItems().removeAll(butaoMoradas.getItems());

                List<PostalAddress> list = cliente.getMoradas();
                for (PostalAddress e : list) {
                    butaoMoradas.getItems().add(e);
                }
                butaoMoradas.getSelectionModel().selectLast();
                butaoMoradas.getItems().add(adicionarEndereco);
            }
        }
        setPreco();
    }

    /**
     * Atualiza a Categoria e os tipos de Serviços que a esta estão associados
     *
     * @param event
     */
    @FXML
    private void clicarCategorias(ActionEvent event) {
        butaoServico.setDisable(false);
        butAdicionarS.setDisable(false);
        butaoServico.getItems().removeAll(butaoServico.getItems());
        descCompleta.setText("");
        setServicosButao();
        setPreco();
    }

    /**
     * Altera o valor de this.changed para o valor de changed
     *
     * @param changed
     */
    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    /**
     * Verifica o serviço selecionado pelo Cliente e atualiza o preço do pedido
     *
     * @param event
     */
    @FXML
    private void clicarServico(ActionEvent event) {
        Service serTotal = butaoServico.getValue();
        fieldHoras.setText("");
        if (serTotal != null) {
            if (serTotal.getTime() != null) {
                fieldHoras.setText(serTotal.getTime().toString());
                butaoUp.setDisable(true);
                butaoDown.setDisable(true);
            } else {
                fieldHoras.setText(String.format("%02d:%02d", Fhora, Fminuto));
                butaoUp.setDisable(false);
                butaoDown.setDisable(false);
            }

            RegisterService s = empresa.getRegistoServico();
            descCompleta.setText("Descricao: " + serTotal.getDescriptionFull());
            setPreco();
        }
    }

    /**
     * Valida e cria um novo pedido de serviço associado ao cliente que tem sessão iniciada na aplicação
     *
     * @param event
     */
    @FXML
    private void clicarConfirmar(ActionEvent event) {
        try {
            if (checkInfo()) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmar Pedido");
                alert.setHeaderText("Estes dados estão correctos?");
                String pedidoHorarios = "";
                for (int i = 0; i < horarios.size(); i++) {
                    if (i == 2) {
                        pedidoHorarios += horarios.get(i) + " ou etc.";
                        break;
                    } else if (i == horarios.size() - 1) {
                        pedidoHorarios += horarios.get(i);
                    } else {
                        pedidoHorarios += horarios.get(i) + " ou ";
                    }
                }
                String ls = "";
                for (Service s : listaServicos) {
                    ls += s.getId() + "; ";
                }

                alert.setContentText(String.format("Servicos: %s%nPreco: %s€%n%nPara: %s durante %s%nMorada: %s%nInformacao Adicional:%n \"%s\"", ls, priceTotal, pedidoHorarios, fieldHoras.getText(), pedidoMorada, pedidoAdicional));

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    Stage stage = (Stage) butaoConfirmar.getScene().getWindow();
                    RegisterRequest rp = empresa.getRegistoPedido();

                    cControler.setSucesso(rp.registaPedido(rp.novoPedido(cliente, pedidoMorada, listaServicos, horarios, pedidoAdicional, priceTotal, listExecTime)));
                    stage.close();
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
            }
        } catch (NumberFormatException nfe) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Horas Inválidas");

            alert.showAndWait();
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
        }
    }

    /**
     * Mostra a cena criada em criarJanelaPedido()
     *
     * @param event
     */
    @FXML
    private void clicarHorarios(ActionEvent event) {
        novoPedidoStage.show();
    }

    /**
     * Atualiza o preçario do pedido
     *
     * @param event
     */
    @FXML
    private void clicarHoras(KeyEvent event) {
        setPreco();
    }

    /**
     * Aumenta o tempo de duração do pedido e atualiza o preçario do mesmo
     *
     * @param event
     */
    @FXML
    private void clicarUp(ActionEvent event) {
        Fminuto += 30;
        if (Fminuto >= 60) {
            Fminuto = 0;
            Fhora += 1;
        }

        fieldHoras.setText(String.format("%02d:%02d", Fhora, Fminuto));
        setPreco();
    }

    @FXML
    private void butaoAdicionarS(ActionEvent event) {
        Service temp = butaoServico.getValue();
        if (!fieldHoras.getText().equals("00:00")) {
            if (pc.checkListaServico(listaServicos, temp)) {
                priceTotal += priceAdd;
                butaoPreco.setText(String.format("%.2f€", priceTotal));
                listaServicos.add(temp);
                listExecTime.add(new Time(fieldHoras.getText().trim()));
            }
        }
    }

    /**
     * Diminui o tempo de duração do pedido e atualiza o preçario do mesmo
     *
     * @param event
     */
    @FXML
    private void clicarDown(ActionEvent event) {
        if (Fhora != 0 || Fminuto == 30) {
            Fminuto -= 30;
            if (Fminuto < 0) {
                Fminuto = 30;
                Fhora -= 1;
            }
        }

        fieldHoras.setText(String.format("%02d:%02d", Fhora, Fminuto));
        setPreco();
    }

    /**
     * Cria e mostra uma nova cena
     */
    private void abrirJanelaEndereco() {
        try {
            Stage novoEnderecoStage = new Stage();
            novoEnderecoStage.initModality(Modality.APPLICATION_MODAL);

            novoEnderecoStage.setTitle("Adicionar Endereço Postal");
            novoEnderecoStage.setResizable(true);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnEndereco.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            AddressUI ncController = loader.getController();
            ncController.associarController(this);
            novoEnderecoStage.setScene(scene);
            novoEnderecoStage.showAndWait();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Cria uma nova cena
     */
    private void criarJanelaPedido() {
        try {
            novoPedidoStage = new Stage();
            novoPedidoStage.initModality(Modality.APPLICATION_MODAL);

            novoPedidoStage.setTitle("Adicionar Horarios");
            novoPedidoStage.setResizable(true);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnHorarios.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            ListaHorariosUI ncController = loader.getController();
            ncController.associarController(this);
            novoPedidoStage.setScene(scene);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Retorna os horarios associados ao pedido
     *
     * @return horarios
     */
    public List<String> getHorarios() {
        return horarios;
    }

    /**
     * Verifica se todos os dados estão corretos para a criação do pedido
     *
     * @return true, o pedido é válido
     * @return false, o pedido é inválido
     */
    private boolean checkInfo() throws Exception {
        if (butaoMoradas.getValue() == null || butaoMoradas.getValue() == adicionarEndereco || butaoCategorias.getValue() == null || listaServicos.size() == 0 || horarios.size() == 0 || butaoPreco.getText().equals("")) {
            throw new Exception("Dados Insufcientes");
        }
        pedidoMorada = butaoMoradas.getValue();
        pedidoCategoria = butaoCategorias.getValue();
        pedidoAdicional = butaoTexto.getText();

        return true;
    }

    /**
     * Regista o preço do pedido
     */
    private void setPreco() {
        try {
            precoAdicional = pc.calculoPrecoAdicional(butaoMoradas.getValue().getPostalCode().getCod());
            if (butaoServico.getValue() != null && butaoMoradas.getValue() != null && !fieldHoras.getText().equals("00:00") && precoAdicional != -1) {
                butAdicionarS.setDisable(false);
                RegisterService serv = empresa.getRegistoServico();
                double precoBase = butaoServico.getValue().getValue();
                String[] temp = fieldHoras.getText().split(":");
                double horas = Integer.parseInt(temp[0].trim());
                if (temp[1].trim().equals("30")) {
                    horas += 0.5;
                }
                priceAdd = (precoBase * horas) + precoAdicional;
                textAdd.setText(String.format("+ %.2f€", priceAdd));
            } else {
                priceAdd = 0;
                textAdd.setText("");
                butAdicionarS.setDisable(true);
            }
        } catch (NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
        }
    }

    /**
     * Recebe a classe que criou a nova janela
     *
     * @param cController classe que criou a nova janela
     */
    public void associarController(ClientUI cController) {
        this.cControler = cController;
        descCompleta.setText("");
        init();
    }

    /**
     * Devolte o Cliente que está com sessão iniciada na Aplicação
     *
     * @return cliente que está com sessão iniciada na Aplicação
     */
    public Client getCliente() {
        return cliente;
    }

    /**
     * Adiciona os enderecos postais associados ao cliente que tem sessão iniciada e as categorias de serviços para que o cliente possa escolher o tipo de serviço que pretende
     */
    private void init() {
        cliente = cControler.getCliente();
        List<PostalAddress> list = cliente.getMoradas();
        for (PostalAddress e : list) {
            butaoMoradas.getItems().add(e);
        }
        butaoMoradas.getSelectionModel().selectFirst();
        butaoMoradas.getItems().add(adicionarEndereco);

        app = cControler.getApp();
        empresa = app.getEmpresa();

        RegisterCategory cat = empresa.getRegistoCategoria();
        List<Category> listcat = cat.getCategorias();
        for (Category c : listcat) {
            butaoCategorias.getItems().add(c.toString());
        }
    }

    /**
     * Adiciona ao à lista de Serviços butaosServico os serviços que estão associados à categoria selecionada
     */
    private void setServicosButao() {
        String catTotal = butaoCategorias.getValue();
        String[] catArr = catTotal.split("-");
        String catCod = catArr[0].trim();

        RegisterService serv = empresa.getRegistoServico();
        List<Service> listserv = serv.getServicos(catCod);
        for (Service s : listserv) {
            butaoServico.getItems().add(s);
        }
        butaoServico.getSelectionModel().selectFirst();
    }

}