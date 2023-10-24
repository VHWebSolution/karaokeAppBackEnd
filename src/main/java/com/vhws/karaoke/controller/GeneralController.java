package com.vhws.karaoke.controller;

import com.vhws.karaoke.dto.*;
import com.vhws.karaoke.model.FavMusic;
import com.vhws.karaoke.model.House;
import com.vhws.karaoke.model.MusicOut;
import com.vhws.karaoke.model.PlayList;
import com.vhws.karaoke.request.HouseRequestAdd;
import com.vhws.karaoke.request.UserRequestAdd;
import com.vhws.karaoke.request.UserRequestFav;
import com.vhws.karaoke.response.*;
import com.vhws.karaoke.service.GeneralService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Controller Geral, tem o propósito de encaminhar as rotas gerais do projeto,
 * neste controller estão os mappings que podem ser acessados por duas ou mais entidades, seu mapeamento é ("").
 */
@RestController
@RequestMapping("")
public class GeneralController {
    @Autowired
    GeneralService generalService;

    /**
     * Este Mapping é responsavel por gerar um QrCode que encaminhará o usuario para a validação de checkin,
     * ele é mapeado por ("/checkIn");
     * @return QrCode
     */
    @GetMapping("/QRCodeCheckIn") //mudarURL
    public ResponseEntity<byte[]> generateQRCode() {
        String redirectToLogin = "https://vhwebsolutions.com.br/";
        byte[] qrcodeBytes = generateQRCodeAsBytes(redirectToLogin,200,200);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(qrcodeBytes.length);
        return new ResponseEntity<>(qrcodeBytes, headers, HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por apresentar os bares atualmentes abertos ou fechados dependendo da condição do parametro open,
     * ele é mapeado por ("/currentlyOpen/{open}");
     * @param open é um parametro boolean que decidira a condição do bar estar aberto ou fechado;
     * @return uma lista de bares;
     */
    @GetMapping("/currentlyOpen/{open}")
    public ResponseEntity<List<HouseMinResponse>> currentlyOpen(@PathVariable boolean open){
        List<HouseMinResponse> openHouses = generalService.currentlyOpen(open);
        return new ResponseEntity<>(openHouses, HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por apresentar as musicas ja tocadas em um bar
     * ele é mapeado por ("/getPreviousSongs/{id}")
     * @param id é a identificação do bar;
     * @return uma lista de musicas;
     */
    @GetMapping("/getPreviousSongs/{id}") public ResponseEntity<List<MusicOutDTO>> previousSongs(@PathVariable long id){
        List<MusicOutDTO> previewSongs = generalService.previousSongs(id);
        return new ResponseEntity<>(previewSongs, HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por apresentar as musicas que ainda vão ser tocadas em um bar
     * ele é mapeado por ("/getUpcomingSongs/{id}");
     * @param id é a identificação do bar;
     * @return uma lista de musicas;
     */
    @GetMapping("/getUpcomingSongs/{id}")
    public ResponseEntity<List<MusicInDTO>> upcomingSongs(@PathVariable long id){
        List<MusicInDTO> upcomingSongs = generalService.upcomingSongs(id);
        return new ResponseEntity<>(upcomingSongs, HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por fazer a pesquisa das musicas por titulo, album ou artista
     * ele é mapeado por ("/searchMusic/{query}");
     * @param query é o parametro de pesquisa.
     * @return uma lista de musicas;
     */
    @GetMapping("/searchMusic/{query}")
    public ResponseEntity<List<MusicDTO>> searchMusic(@PathVariable String query){
        List<MusicDTO> music = generalService.searchMusic(query);
        return new ResponseEntity<>(music, HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por apresentar os proximos cantores em um bar
     * ele é mapeado por ("/nextSinger/{id}");
     * @param id é a identificação do bar
     * @return uma lista de usuarios;
     */
    @GetMapping("/nextSinger/{id}")
    public ResponseEntity<List<UserResponse>> nextSinger(@PathVariable long id){
        List<UserResponse> nextSingers = generalService.nextSinger(id);
        return new ResponseEntity<>(nextSingers, HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por fazer o checkout do usuario, retirando ele da lista de clientes dentro do bar e retirando sua musica da playlist do bar, caso ele fosse cantas
     * ele é mapeado por ("/checkOut/{houseId}/{costumerId}");
     * @param houseId identificação do bar;
     * @param costumerId identificação do cliente;
     * @return uma lista de usuarios que ainda estão dentro do bar;
     */
    @GetMapping("/checkOut/{houseId}/{costumerId}")
    public ResponseEntity<List<UserResponse>> checkout(@PathVariable("houseId") long houseId, @PathVariable("costumerId") long costumerId){
        List<UserResponse> costumers = generalService.checkout(houseId, costumerId);
        return new ResponseEntity<>(costumers, HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por apresentar todas as playlists de um usuario
     * ele é mapeado por ("/findAllPlayLists/{id}");
     * @param id identificação do usuario;
     * @return retorna uma lista de playlists;
     */
    @GetMapping("/findAllPlayLists/{id}")
    public ResponseEntity<List<PlayListDTO>> findAllPlayLists(@PathVariable long id){
        List<PlayListDTO> playListDTO = generalService.findAllPlayLists(id);
        return new ResponseEntity<>(playListDTO, HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por apresentar todos os amigos de um usuario
     * ele é mapeado por ("/findAllFriends/{id}")
     * @param id identificação do usuario
     * @return uma lista de usuarios;
     */
    @GetMapping("/findAllFriends/{id}")
    public ResponseEntity<List<UserResponseFriend>> findAllFriends(@PathVariable long id){
        List<UserResponseFriend> friendList = generalService.findAllFriends(id);
        return new ResponseEntity<>(friendList, HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por apresentar uma lista de clientes que ainda estão no bar
     * ele é mapeado por ("/findAllCostumers/{id}");
     * @param id identificação do bar;
     * @return uma lista de users;
     */
    @GetMapping("/findAllCostumers/{id}")
    public ResponseEntity<List<UserResponseCostumer>> findAllCostumers(@PathVariable long id){
        List<UserResponseCostumer> costumers = generalService.findAllCostumers(id);
        return new ResponseEntity<>(costumers, HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por apresentar todas as musicas na lista de musicas favoritas do usuario;
     * @param id identificação do usuario
     * @return uma lista de musicas favoritas
     */
    @GetMapping("/findAllFavSongs/{id}")
    public ResponseEntity<List<FavMusicDTO>> findAllFavSongs(@PathVariable long id){
        List<FavMusicDTO> musicDTO = generalService.findAllFavSongs(id);
        return new ResponseEntity<>(musicDTO, HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por apresentar todas as solicitações de amizade enviadas
     * @param id identificação do usuario
     * @return
     */
    @GetMapping("/findAllRequestsDispatched/{id}")
    public ResponseEntity<List<UserResponseRequest>> findAllRequestsDispatcher(@PathVariable long id){
        List<UserResponseRequest> userResponseRequest = generalService.findAllRequestsDispatched(id);
        return new ResponseEntity<>(userResponseRequest,HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por apresentar todas as solicitações de amizade recebidas
     * @param id identificação do usuario
     * @return retorna uma lista de usuarios
     */
    @GetMapping("/findAllRequestsReceived/{id}")
    public ResponseEntity<List<UserResponseRequest>> findAllRequestsReceived(@PathVariable long id){
        List<UserResponseRequest> userResponseRequest = generalService.findAllRequestsReceived(id);
        return new ResponseEntity<>(userResponseRequest,HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por apresentar todas as musicas disponiveis no BD
     * ele é mapeado por ("/findAllSongs")
     * @return uma lista de musicas
     */
    @GetMapping("/findAllSongs")
    public ResponseEntity<List<MusicDTO>> findAllSongs(){
        List<MusicDTO> musicDTOList = generalService.findAllSongs();
        return new ResponseEntity<>(musicDTOList, HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por apresentar os detalhes completos do usuario
     * @param id identificação do usuario
     * @return retorna o usuario completo
     */
    @GetMapping("userFullDetails/{id}")
    public ResponseEntity<UserDTO> userFullDetails(@PathVariable long id){
        UserDTO userDTO = generalService.userFullDetails(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por apresentar as informações parciais do usuario
     * @param id identificação do usuario
     * @return retorna o usuario
     */
    @GetMapping("userPartialDetails/{id}")
    public ResponseEntity<UserResponseCostumer> userPartialDetails(@PathVariable long id){
        UserResponseCostumer userResponseCostumer = generalService.userPartialDetails(id);
        return new ResponseEntity<>(userResponseCostumer, HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por apresentar as informações completas do bar
     * @param id identificação do bar
     * @return retorna o bar completo
     */
    @GetMapping("/houseFullDetails/{id}")
    public ResponseEntity<HouseDTO> houseFullDetails(@PathVariable long id){
        HouseDTO houseDTO = generalService.houseFullDetails(id);
        return new ResponseEntity<>(houseDTO, HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por apresentar as informações parciais do bar
     * @param id identificação do bar
     * @return retorna o bar parcialmente
     */
    @GetMapping("/housePartialDetails/{id}")
    public ResponseEntity<HouseMinResponse> housePartialDetails(@PathVariable long id){
        HouseMinResponse houseMinResponse = generalService.housePartialDetails(id);
        return new ResponseEntity<>(houseMinResponse, HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por apresentar os detalhes de uma playlist
     * @param id identificação da playlist
     * @return retorna a playlist
     */
    @GetMapping("/getPlayList/{id}")
    public ResponseEntity<PlayListDTO> getPlayList(@PathVariable long id){
        PlayListDTO playListDTO = generalService.getPlayList(id);
        return new ResponseEntity<>(playListDTO, HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por apresentar os detalhes de uma musica
     * @param id identificação da musica
     * @return retorna a musica
     */
    @GetMapping("/getMusic/{id}")
    public ResponseEntity<MusicDTO> getMusic(@PathVariable long id){
        MusicDTO musicDTO = generalService.getMusic(id);
        return new ResponseEntity<>(musicDTO, HttpStatus.OK);
    }
    /**
     * Este Mapping é responsavel por adicionar uma musica e cantor na lista de proximos a cantar do bar
     * ele é mapeado por ("/addToQueue/{houseId}/{costumerId}")
     * @param houseId identificação do bar
     * @param costumerId identificação do usuario
     * @param music é a classe que serve de estrutura para o corpo do JSON
     * @return a lista de proximas musicas
     */
    @PostMapping("/addToQueue/{houseId}/{costumerId}/{musicId}")
    public  ResponseEntity<List<MusicInDTO>> addToQueue(@PathVariable("houseId") long houseId, @PathVariable("costumerId") long costumerId, @PathVariable long musicId){
        List<MusicInDTO> musicInQueue = generalService.addToQueue(houseId,costumerId, musicId);
        return new ResponseEntity<>(musicInQueue, HttpStatus.CREATED);
    }

    /**
     * Este Mapping é responsavel por criar a conta de um bar
     * ele é mapeado por ("/addHouse");
     * @param houseRequestAdd é a classe que serve de estrutura para o corpo do JSON
     * @return o bar adicionado
     */
    @PostMapping("/addHouse")
    public ResponseEntity<HouseMinResponse> addHouse(@RequestBody HouseRequestAdd houseRequestAdd){
        HouseMinResponse houseMinResponse = new HouseMinResponse();
        houseMinResponse = generalService.addHouse(houseRequestAdd);
        return new ResponseEntity<>(houseMinResponse, HttpStatus.CREATED);
    }

    /**
     * Este Mapping é responsavel por criar um usuario comum
     * ele é mapeado por ("/addCostumer");
     * @param userRequestAdd é a classe que serve de estrutura para o corpo do JSON
     * @returno o usuario adicionado
     */
    @PostMapping("/addCostumer")
    public ResponseEntity<UserResponseAdd> addCostumer(@RequestBody UserRequestAdd userRequestAdd){
        UserResponseAdd userResponseAdd = new UserResponseAdd();
        userResponseAdd = generalService.addCostumer(userRequestAdd);
        return new ResponseEntity<>(userResponseAdd, HttpStatus.CREATED);
    }

    /**
     * Este Mapping é responsavel por adicionar musicas no BD
     * ele é mapeado por ("/addMusicToDB");
     * @param musicDTO é a classe que serve de estrutura para o corpo do JSON
     * @return a musica adicionada
     */
    @PostMapping("/addMusicToDB")
    public ResponseEntity<MusicDTO> addMusicToDB(@RequestBody MusicDTO musicDTO){
        musicDTO = generalService.addMusicToDB(musicDTO);
        return new ResponseEntity<>(musicDTO, HttpStatus.CREATED);
    }

    /**
     * Este Mapping é responsavel por adicionar uma playplist para o usuario
     * ele é mapeado por ("/addPlayList/{id}");
     * @param playListDTO é a classe que serve de estrutura para o corpo do JSON
     * @param id identificação do usuario
     * @return a playlist adicionada
     */
    @PostMapping("/addPlayList/{id}")
    public ResponseEntity<PlayListDTO> addPlayList(@RequestBody PlayListDTO playListDTO, @PathVariable long id){
        playListDTO = generalService.addPlayList(playListDTO, id);
        return new ResponseEntity<>(playListDTO, HttpStatus.CREATED);
    }

    /**
     * Este Mapping é responsavel por adicionar uma musica a lista de musicas anteriores
     * @param houseId idetificação do bar
     * @param musicID idetificação da musica
     * @param userId idetificação do usuario
     * @return retorna a musica que foi realocada
     */
    @PostMapping("/addPreviousSong/{houseId}/{musicId}/{userId}")
    public ResponseEntity<MusicOutDTO> addPreviousSong(@PathVariable("houseId") long houseId, @PathVariable("musicId") long musicID, @PathVariable("userId") long userId){
        MusicOutDTO musicOutDTO = generalService.addPreviousSong(houseId, musicID, userId);
        return new ResponseEntity<>(musicOutDTO, HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por adicionar uma musica aos favoritos
     * ele é mapeado por ("/addToFav/{id}");
     * @param id identificação do usuario
     * @param userRequestFav é a classe que serve de estrutura para o corpo do JSON
     * @return a musica adicionada
     */
    @PutMapping("/addToFav/{id}/{musicId}")
    public ResponseEntity<UserResponseFav> addToFav(@PathVariable long id,@PathVariable("musicId") long musicId){
        UserResponseFav userResponseFav = generalService.addToFav(id, musicId);
        return new ResponseEntity<>(userResponseFav, HttpStatus.CREATED);
    }

    /**
     * Este Mapping é responsavel por enviar um pedido de amizade entre usuarios
     * ele é mapeado por ("sendFriendRequest/{userId}/{friendId}");
     * @param userId identificação do usuario que enviou o pedido de amizade
     * @param friendId identificação do amigo requisitado
     * @return lista de amigos
     */
    @PutMapping("/sendFriendRequest/{userId}/{friendId}")
    public ResponseEntity<List<UserResponseFriend>> sendFriendRequest(@PathVariable("userId") long userId, @PathVariable("friendId") long friendId){
        List<UserResponseFriend> userResponseFriend = new ArrayList<>();
        userResponseFriend = generalService.sendFriendRequest(userId, friendId);
        return new ResponseEntity<>(userResponseFriend, HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por aceitar os pedidos de amizade enviados entre usuarios
     * ele é mapeado por ("/acceptRequest/{userId}/{friendId}");
     * @param userId identificação do usuario que esta aceitando o  pedido
     * @param friendId identificação do usuario que fez a requisição
     * @return uma lista de amigos
     */
    @PutMapping("/acceptRequest/{userId}/{friendId}")
    public ResponseEntity<List<UserResponseFriend>> acceptFriendRequest(@PathVariable("userId") long userId, @PathVariable("friendId") long friendId){
        List<UserResponseFriend> userResponseFriend = new ArrayList<>();
        userResponseFriend = generalService.acceptFriendRequest(userId, friendId);
        return new ResponseEntity<>(userResponseFriend, HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por adicionar musicas a uma determinada playlist
     * ele é mapeado por ("/addToPlayList/{id}")
     * @param id é a identificação da playlist
     * @param musicDTO é a classe que serve de estrutura para o corpo do JSON
     * @return a playlist adicionada
     */
    @PutMapping("/addToPlayList/{id}/{musicId}")
    public ResponseEntity<PlayListDTO> addToPlayList(@PathVariable long id, @PathVariable long musicId){
        PlayListDTO playListDTO = generalService.addToPlayList(id, musicId);
        return new ResponseEntity<>(playListDTO, HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por fazer a validação do checkin de um usuario no bar
     * @param costumerId identificação do usuario
     * @param houseId identificação do bar
     * @return retorna uma resposta de checkin
     */
    @PutMapping("/checkInValidation/{costumerId}/{houseId}")
    public ResponseEntity<CheckInResponse> checkInValidation(@PathVariable("costumerId") long costumerId, @PathVariable("houseId") long houseId){
        CheckInResponse checkInResponse = generalService.checkInValidation(costumerId, houseId);
        return new ResponseEntity<>(checkInResponse, HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por abrir o bar
     * @param id identificação do bar
     * @return retorna uma mensagem de texto
     */
    @PutMapping("/openBar/{id}")
    public ResponseEntity<?> openBar(@PathVariable long id){
        String message = generalService.openBar(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     *Este Mapping é responsavel por fechar o bar
     * @param id identificação do bar
     * @return retorna uma mensagem de texto
     */
    @PutMapping("/closeBar/{id}")
    public ResponseEntity<?> closeBar(@PathVariable long id){
        String message = generalService.closeBar(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por remover uma musica de uma playlist
     * @param musicId identificação da musica
     * @param playlistId identificação da playlisy
     * @return retorna uma mensagem de texto
     */
    @PutMapping("/removeMusicFromPlaylist/{musicId}/{playlistId}")
    public ResponseEntity<?> removeMusicFromPlaylist(@PathVariable long musicId, @PathVariable long playlistId){
        generalService.removeMusicFromPlaylist(musicId, playlistId);
        return new ResponseEntity<>("Music removed with succes!", HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por remover uma musica dos favoritos
     * @param musicId identificação da musica
     * @param playlistId identificação da playlist
     * @return retorna uma mensagem de texto
     */
    @PutMapping("/removeMusicFromFav/{musicId}/{playlistId}")
    public ResponseEntity<?> removeMusicFromFav(@PathVariable long musicId, @PathVariable long playlistId){
        generalService.removeMusicFromFav(musicId, playlistId);
        return new ResponseEntity<>("Music removed with succes!", HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por remover um usuario dos proximos cantores
     * @param houseId identificação do bar
     * @param userId identificação do usuario
     * @return retorna uma lista de usuario que ainda estão na lista
     */
    @PutMapping("/removeFromNextSinger/{houseId}/{userId}")
    public ResponseEntity<List<UserResponseCostumer>> removeFromNextSinger(@PathVariable long houseId, @PathVariable long userId){
        List<UserResponseCostumer> userResponseCostumer = generalService.removeFromNextSinger(houseId, userId);
        return new ResponseEntity<>(userResponseCostumer, HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por deletar a conta de usuario
     * @param id identificação do usuario
     * @return retorna uma mensagem de texto
     */
    @DeleteMapping("/deleteUserAccount/{id}")
    public ResponseEntity<?> deleteUserAccount(@PathVariable long id){
        generalService.deleteUserAccount(id);
        return  new ResponseEntity<>("Account deleted with success!", HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por deletar a conta do bar
     * @param id identificação do bar
     * @return retorna uma mensagem de texto
     */
    @DeleteMapping("/deleteBarAccount/{id}")
    public ResponseEntity<?> deleteBarAccount(@PathVariable long id){
        generalService.deleteBarAccount(id);
        return  new ResponseEntity<>("Account deleted with success!", HttpStatus.OK);
    }

    /**
     * Este Mapping é responsavel por deletar uma playlist
     * @param playListId identificação da playlist
     * @return retorna uma mensagem de texto
     */
    @DeleteMapping("/deletePlayList/{playListId}")
    public ResponseEntity<?> deletePlayList(@PathVariable long playListId){
        generalService.deletePlayList(playListId);
        return  new ResponseEntity<>("PlayList deleted with success!", HttpStatus.OK);
    }
    //----------------------------------------------------------------------------------------------------------------------------------------
    public byte[] generateQRCodeAsBytes(String text, int width, int height) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

            MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(0xFF000000, 0xFFFFFFFF);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream, matrixToImageConfig);

            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
