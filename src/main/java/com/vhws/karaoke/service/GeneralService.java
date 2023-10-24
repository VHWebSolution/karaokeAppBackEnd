package com.vhws.karaoke.service;

import com.vhws.karaoke.dto.*;
import com.vhws.karaoke.model.*;
import com.vhws.karaoke.repository.*;
import com.vhws.karaoke.request.HouseRequestAdd;
import com.vhws.karaoke.request.UserRequestAdd;
import com.vhws.karaoke.request.UserRequestFav;
import com.vhws.karaoke.response.*;
import jakarta.persistence.EntityManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GeneralService {
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MusicRepository musicRepository;
    @Autowired
    private PlayListRepository playListRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private MusicInRepository musicInRepository;

    public List<HouseMinResponse> currentlyOpen(boolean open) {
        List<House> openHouses = houseRepository.findByOpenStatus(open);
        if (openHouses.isEmpty())
            throw new Error();
        List<HouseMinResponse> houseMinResponses = new ArrayList<>();
        for (House openHouse : openHouses) {
            HouseMinResponse houseMinResponse = new HouseMinResponse();
            houseMinResponse.setHouseId(openHouse.getHouseId());
            houseMinResponse.setHouseName(openHouse.getHouseName());
            houseMinResponse.setAddress(openHouse.getAddress());
            houseMinResponse.setCostumersIn(openHouse.getCostumersIn());
            houseMinResponse.setDescription(openHouse.getDescription());
            houseMinResponse.setOperatingHours(openHouse.getOperatingHours());
            houseMinResponse.setPhone(openHouse.getPhone());
            houseMinResponse.setPicture(openHouse.getPicture());
            houseMinResponses.add(houseMinResponse);
        }
        return houseMinResponses;
    }

    public List<MusicOutDTO> previousSongs(long id) {
        Optional<House> houseOptional = houseRepository.findById(id);
        if (houseOptional.isEmpty())
            throw new Error();
        House house = houseOptional.get();
        List<MusicOut> songs = house.getMusicOutOfList();
        List<MusicOutDTO> songsDTO = new ArrayList<>();
        for (MusicOut song : songs) {
            MusicOutDTO songDTO = new MusicOutDTO();
            songDTO.setMusicOutId(song.getMusicOutId());
            songDTO.setTitle(song.getTitle());
            songDTO.setArtist(song.getArtist());
            songDTO.setAlbum(song.getAlbum());
            songsDTO.add(songDTO);
        }
        return songsDTO;
    }

    public List<MusicInDTO> upcomingSongs(long id) {
        Optional<House> houseOptional = houseRepository.findById(id);
        if (houseOptional.isEmpty())
            throw new Error();
        House house = houseOptional.get();
        List<MusicIn> songs = house.getMusicInList();
        List<MusicInDTO> songsDTO = new ArrayList<>();
        for (MusicIn song : songs) {
            MusicInDTO songDTO = new MusicInDTO();
            songDTO.setMusicInId(song.getMusicInId());
            songDTO.setTitle(song.getTitle());
            songDTO.setArtist(song.getArtist());
            songDTO.setAlbum(song.getAlbum());
            songsDTO.add(songDTO);
        }
        return songsDTO;
    }

    public List<MusicDTO> searchMusic(String query) {
        List<Music> songs = musicRepository.findMusicsByCriteria(query);
        if(songs.isEmpty())
            throw new Error();
        List<MusicDTO> musicDTOList = new ArrayList<>();
        for(Music song:songs){
            MusicDTO musicDTO = new MusicDTO(song.getMusicId(), song.getTitle(), song.getArtist(), song.getAlbum(), song.getLink());
            musicDTOList.add(musicDTO);
        }
        return musicDTOList;
    }

    public List<UserResponse> nextSinger(long id) {
        Optional<House> houseOptional = houseRepository.findById(id);
        if (houseOptional.isEmpty())
            throw new Error();
        House house = houseOptional.get();
        List<User> nextSingers = house.getNextSingerList();
        List<UserResponse> nextSingersDTO = new ArrayList<>();
        for (User nextSinger : nextSingers) {
            UserResponse nextSingerDTO = new UserResponse();
            nextSingerDTO.setName(nextSinger.getName());
            nextSingerDTO.setLastName(nextSinger.getLastName());
            nextSingerDTO.setMusic(nextSinger.getNextMusic());
            nextSingerDTO.setPicture(nextSinger.getPicture());
            nextSingersDTO.add(nextSingerDTO);
        }
        return nextSingersDTO;
    }

    public List<PlayListDTO> findAllPlayLists(long id){
        Optional<User> userOpt = userRepository.findById(id);
        if(userOpt.isEmpty())
            throw new Error();
        User user = userOpt.get();
        List<PlayList> playLists = user.getPlayList();
        List<PlayListDTO> playListDTOS = new ArrayList<>();
        for(PlayList playList : playLists){
            PlayListDTO playListDTO = new PlayListDTO(playList.getPlayListId(), playList.getName(), playList.getPicture(), playList.getSongList());
            playListDTOS.add(playListDTO);
        }
        return playListDTOS;
    }
    public  List<UserResponseFriend> findAllFriends(long id){
        Optional<User> userOpt = userRepository.findById(id);
        if(userOpt.isEmpty())
            throw new Error();
        User user = userOpt.get();
        List<User> users = user.getFriends();
        List<UserResponseFriend> userResponseFriendList= new ArrayList<>();
        for(User friend : users){
            UserResponseFriend userResponseFriend = new UserResponseFriend(friend.getName(), friend.getLastName());
            userResponseFriendList.add(userResponseFriend);
        }
        return userResponseFriendList;
    }
    public List<UserResponseCostumer> findAllCostumers(long id){
        Optional<House> houseOpt = houseRepository.findById(id);
        if(houseOpt.isEmpty())
            throw new Error();
        House house = houseOpt.get();
        List<User> users = house.getCostumersIn();
        List<UserResponseCostumer> costumers = new ArrayList<>();
        for(User user:users){
            UserResponseCostumer costumer = new UserResponseCostumer(user.getUserId(),user.getName(),user.getLastName(),
                    user.getBirthDay(),user.getGender(),user.getAddress(),user.getPhone(),user.getPicture());
            costumers.add(costumer);
        }
        return costumers;
    }

    public List<UserResponseRequest> findAllRequestsDispatched(long id){
        Optional<User> userOpt = userRepository.findById(id);
        if(userOpt.isEmpty())
            throw new Error();
        User user = userOpt.get();
        List<User> requestDispatcherList = user.getRequestDispatched();
        List<UserResponseRequest> userResponseRequests = new ArrayList<>();
        for(User request:requestDispatcherList){
            UserResponseRequest responseRequest = new UserResponseRequest(request.getUserId(),request.getName(),request.getLastName());
            userResponseRequests.add(responseRequest);
        }
        return userResponseRequests;
    }

    public List<UserResponseRequest> findAllRequestsReceived(long id){
        Optional<User> userOpt = userRepository.findById(id);
        if(userOpt.isEmpty())
            throw new Error();
        User user = userOpt.get();
        List<User> requestReceivedList = user.getRequestReceived();
        List<UserResponseRequest> userResponseRequests = new ArrayList<>();
        for(User request:requestReceivedList){
            UserResponseRequest responseRequest = new UserResponseRequest(request.getUserId(),request.getName(),request.getLastName());
            userResponseRequests.add(responseRequest);
        }
        return userResponseRequests;
    }

    public List<FavMusicDTO> findAllFavSongs(long id){
        Optional<User> userOpt = userRepository.findById(id);
        if(userOpt.isEmpty())
            throw new Error();
        User user = userOpt.get();
        List<FavMusic> favMusicList = user.getFavMusicList();
        List<FavMusicDTO> favMusicDTOS = new ArrayList<>();
        for(FavMusic favMusic:favMusicList){
            FavMusicDTO favMusicDTO = new FavMusicDTO(favMusic.getFavMusicId(), favMusic.getTitle(),favMusic.getArtist(),favMusic.getAlbum(),favMusic.getLink());
            favMusicDTOS.add(favMusicDTO);
        }
        return favMusicDTOS;
    }

    public List<MusicDTO> findAllSongs(){
        List<Music> songs = musicRepository.findAll();
        List<MusicDTO> musicDTOList = new ArrayList<>();
        for(Music song:songs){
            MusicDTO musicDTO = new MusicDTO(song.getMusicId(), song.getTitle(), song.getArtist(), song.getAlbum(), song.getLink());
            musicDTOList.add(musicDTO);
        }
        return musicDTOList;
    }

    public UserDTO userFullDetails(long id){
        Optional<User> userOpt = userRepository.findById(id);
        if(userOpt.isEmpty())
            throw new Error();
        User user = userOpt.get();
        UserDTO userDTO = new UserDTO(user.getUserId(), user.getName(), user.getLastName(), user.getBirthDay(), user.getGender()
                ,user.getCpf(),user.getAddress(),user.getPhone(),user.getPlayList(),user.getFavMusicList(),user.getPicture(),user.getNextMusic()
                ,user.getHouse(),user.getFriends(),user.getRequestDispatched(),user.getRequestReceived());
        return userDTO;
    }

    public UserResponseCostumer userPartialDetails(long id){
        Optional<User> userOpt = userRepository.findById(id);
        if(userOpt.isEmpty())
            throw new Error();
        User user = userOpt.get();
        UserResponseCostumer userResponseCostumer = new UserResponseCostumer(user.getUserId(),user.getName(),user.getLastName(),user.getBirthDay()
                ,user.getGender(),user.getAddress(),user.getPhone(),user.getPicture());
        return userResponseCostumer;
    }

    public HouseDTO houseFullDetails(long id){
        Optional<House> houseOpt = houseRepository.findById(id);
        if(houseOpt.isEmpty())
            throw new Error();
        House house = houseOpt.get();
        HouseDTO houseDTO = new HouseDTO(house.getHouseId(),house.getHouseName(),house.getCnpj(),house.getDescription(),house.getPhone(),house.getAddress()
                ,house.getOperatingHours(), house.getPicture(),house.getCostumersIn(),house.isOpen(),house.getMusicInList(),house.getMusicOutOfList(),house.getNextSingerList());
        return houseDTO;
    }

    public HouseMinResponse housePartialDetails(long id){
        Optional<House> houseOpt = houseRepository.findById(id);
        if(houseOpt.isEmpty())
            throw new Error();
        House house = houseOpt.get();
        HouseMinResponse houseMinResponse = new HouseMinResponse(house.getHouseId(),house.getHouseName()
                ,house.getDescription(),house.getOperatingHours(),house.getAddress(),house.getPhone(),house.getPicture());
        return houseMinResponse;
    }

    public PlayListDTO getPlayList(long id){
        Optional<PlayList> playListOpt = playListRepository.findById(id);
        if(playListOpt.isEmpty())
            throw new Error();
        PlayList playList = playListOpt.get();
        PlayListDTO playListDTO = new PlayListDTO(playList.getPlayListId(),playList.getName(),playList.getPicture(),playList.getSongList());
        return playListDTO;
    }

    public MusicDTO getMusic( long id){
        Optional<Music> musicOpt = musicRepository.findById(id);
        if(musicOpt.isEmpty())
            throw new Error();
        Music music = musicOpt.get();
        MusicDTO musicDTO = new MusicDTO(music.getMusicId(),music.getTitle(),music.getArtist(),music.getAlbum(), music.getLink());
        return musicDTO;
    }
    public List<UserResponse> checkout(long houseId, long costumerId) {
        Optional<House> houseOpt = houseRepository.findById(houseId);
        if (houseOpt.isEmpty())
            throw new Error();
        Optional<User> costumerOpt = userRepository.findById(costumerId);
        if (costumerOpt.isEmpty())
            throw new Error();
        House house = houseOpt.get();
        User costumer = costumerOpt.get();
        List<User> costumersIn = house.getCostumersIn();
        costumersIn.remove(costumer);
        List<User> nextSinger = house.getNextSingerList();
        nextSinger.remove(costumer);
        List<MusicIn> nextSongs = house.getMusicInList();
        nextSongs.remove(costumer.getNextMusic());
        costumer.setHouse(null);
        costumer.setNextMusic(null);
        house.setCostumersIn(costumersIn);
        house.setNextSingerList(nextSinger);
        house.setMusicInList(nextSongs);
        userRepository.save(costumer);
        houseRepository.save(house);
        List<UserResponse> costumersInResponse = new ArrayList<>();
        for (User costumerIn : costumersIn) {
            UserResponse costumerInResponse = new UserResponse();
            costumerInResponse.setName(costumerIn.getName());
            costumerInResponse.setLastName(costumerIn.getLastName());
            costumerInResponse.setMusic(costumerIn.getNextMusic());
            costumerInResponse.setPicture(costumerIn.getPicture());
            costumersInResponse.add(costumerInResponse);
        }
        return costumersInResponse;
    }

    @Transactional
    public List<MusicInDTO> addToQueue(long houseId, long costumerId, long musicId) {
        Optional<House> houseOpt = houseRepository.findById(houseId);
        if (houseOpt.isEmpty())
            throw new Error();
        Optional<User> costumerOpt = userRepository.findById(costumerId);
        if (costumerOpt.isEmpty())
            throw new Error();
        Optional<Music> musicOpt = musicRepository.findById(musicId);
        if (musicOpt.isEmpty())
            throw new Error();
        Music music= musicOpt.get();
        House house = houseOpt.get();
        User costumer = costumerOpt.get();
        if(!house.equals(costumer.getHouse()))
            throw new Error();
        MusicIn musicIn = new MusicIn(music.getTitle(), music.getArtist(), music.getAlbum(), music.getLink());
        musicIn = entityManager.merge(musicIn);
        List<MusicIn> songsInList = house.getMusicInList();
        songsInList.add(musicIn);
        List<User> nextSingers = house.getNextSingerList();
        nextSingers.add(costumer);
        costumer.setNextMusic(music);
        house.setMusicInList(songsInList);
        house.setNextSingerList(nextSingers);
        userRepository.save(costumer);
        houseRepository.save(house);
        List<MusicInDTO> musicInDTOS = new ArrayList<>();
        for (MusicIn song : songsInList) {
            MusicInDTO musicInDTO = new MusicInDTO(song.getMusicInId(), song.getTitle(), song.getArtist(), song.getAlbum(), song.getLink());
            musicInDTOS.add(musicInDTO);
        }

        return musicInDTOS;
    }

    public HouseMinResponse addHouse(HouseRequestAdd houseRequestAdd){
        House house = new House(houseRequestAdd.getHouseName(),houseRequestAdd.getCnpj(), houseRequestAdd.getDescription(),
                houseRequestAdd.getPhone(), houseRequestAdd.getAddress(), houseRequestAdd.getOperatingHours(), houseRequestAdd.getPicture());
        house = houseRepository.save(house);
        HouseMinResponse houseMinResponse = new HouseMinResponse(house.getHouseId(), houseRequestAdd.getHouseName(),houseRequestAdd.getCnpj(), houseRequestAdd.getDescription(),
                houseRequestAdd.getPhone(), houseRequestAdd.getAddress(), houseRequestAdd.getOperatingHours(), houseRequestAdd.getPicture());
        return houseMinResponse;
    }

    public UserResponseAdd addCostumer(UserRequestAdd userRequestAdd){
        User user = new User(userRequestAdd.getName(),userRequestAdd.getLastName(),userRequestAdd.getBirthDay(),
                userRequestAdd.getGender(),userRequestAdd.getCpf(),userRequestAdd.getAddress(),userRequestAdd.getPhone(),userRequestAdd.getPicture());
        user = userRepository.save(user);
        UserResponseAdd userResponseAdd = new UserResponseAdd(user.getUserId(), userRequestAdd.getName(),userRequestAdd.getLastName(),userRequestAdd.getBirthDay(),
                userRequestAdd.getGender(),userRequestAdd.getCpf(),userRequestAdd.getAddress(),userRequestAdd.getPhone(),userRequestAdd.getPicture());
        return userResponseAdd;
    }

    public MusicDTO addMusicToDB(MusicDTO musicDTO){
    Music music = new Music(musicDTO.getTitle(), musicDTO.getArtist(), musicDTO.getAlbum(), musicDTO.getLink());
    music = musicRepository.save(music);
    musicDTO.setMusicId(music.getMusicId());
    return musicDTO;
    }

    public PlayListDTO addPlayList(PlayListDTO playListDTO, long id){
    Optional<User> userOpt = userRepository.findById(id);
    if(userOpt.isEmpty())
        throw new Error();
    User user = userOpt.get();
    List<PlayList> playLists = user.getPlayList();
    PlayList playList = new PlayList(playListDTO.getName(), playListDTO.getPicture(), playListDTO.getSongList());
    playLists.add(playList);
    user.setPlayList(playLists);
    user = userRepository.save(user);
    return  playListDTO;
    }

    public MusicOutDTO addPreviousSong(long houseId, long musicId, long userId){
        Optional<House> houseOpt = houseRepository.findById(houseId);
        if(houseOpt.isEmpty())
            throw new Error();
        Optional<MusicIn> musicOpt = musicInRepository.findById(musicId);
        if(musicOpt.isEmpty())
            throw new Error();
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isEmpty())
            throw new Error();
        User user = userOpt.get();
        House house = houseOpt.get();
        MusicIn music = musicOpt.get();
        List<MusicIn> musicInList = house.getMusicInList();
        musicInList.remove(music);
        MusicOut musicOut = new MusicOut(music.getTitle(), music.getArtist(), music.getAlbum(), music.getLink());
        List<MusicOut> musicOutList = house.getMusicOutOfList();
        musicOutList.add(musicOut);
        List<User> nextSingers = house.getNextSingerList();
        nextSingers.remove(user);
        house.setMusicInList(musicInList);
        house.setMusicOutOfList(musicOutList);
        house.setNextSingerList(nextSingers);
        user.setNextMusic(null);
        userRepository.save(user);
        houseRepository.save(house);
        MusicOutDTO musicOutDTO = new MusicOutDTO(music.getTitle(), music.getArtist(), music.getAlbum(), music.getLink());
        return musicOutDTO;
    }

    public UserResponseFav addToFav(long id, long musicId){
        Optional<User> userOpt = userRepository.findById(id);
        if(userOpt.isEmpty())
            throw new Error();
        Optional<Music> musicOpt = musicRepository.findById(musicId);
        if(musicOpt.isEmpty())
            throw new Error();
        Music music = musicOpt.get();
        User user = userOpt.get();
        FavMusic favMusic = new FavMusic(music.getTitle(), music.getArtist(), music.getAlbum(), music.getLink());
        List<FavMusic> favMusicList = user.getFavMusicList();
        favMusicList.add(favMusic);
        user.setFavMusicList(favMusicList);
        user = userRepository.save(user);
        UserResponseFav userResponseFav = new UserResponseFav(user.getName(), user.getLastName(), user.getFavMusicList());
        return userResponseFav;
    }

    public List<UserResponseFriend> sendFriendRequest(long userId, long friendId){
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty())
            throw new Error();
        Optional<User> friendOpt = userRepository.findById(friendId);
        if (friendOpt.isEmpty())
            throw new Error();
        User user = userOpt.get();
        User friend = friendOpt.get();
        List<User> requestDispatched = user.getRequestDispatched();
        requestDispatched.add(friend);
        List<User> requestReceived = friend.getRequestReceived();
        requestReceived.add(user);
        user.setRequestDispatched(requestDispatched);
        friend.setRequestReceived(requestReceived);
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(friend);
        userRepository.saveAll(users);
        List<UserResponseFriend> userResponseFriends = new ArrayList<>();
        for(User request:requestDispatched){
            UserResponseFriend userResponseFriend = new UserResponseFriend(request.getName(), request.getLastName());
            userResponseFriends.add(userResponseFriend);
        }
        return userResponseFriends;
    }

    public List<UserResponseFriend> acceptFriendRequest(long userId, long friendId){
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty())
            throw new Error();
        Optional<User> friendOpt = userRepository.findById(friendId);
        if (friendOpt.isEmpty())
            throw new Error();
        User user = userOpt.get();
        User friend = friendOpt.get();
        List<User> requestDispatched = friend.getRequestDispatched();
        requestDispatched.remove(user);
        List<User> requestReceived = user.getRequestReceived();
        requestReceived.remove(friend);
        List<User> friendsUser = user.getFriends();
        friendsUser.add(friend);
        List<User> friendsFriend = friend.getFriends();
        friendsFriend.add(user);
        friend.setFriends(friendsFriend);
        friend.setRequestDispatched(requestDispatched);
        user.setFriends(friendsUser);
        user.setRequestReceived(requestReceived);
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(friend);
        userRepository.saveAll(users);
        List<UserResponseFriend> userResponseFriends = new ArrayList<>();
        for(User request:friendsUser){
            UserResponseFriend userResponseFriend = new UserResponseFriend(request.getName(), request.getLastName());
            userResponseFriends.add(userResponseFriend);
        }
        return userResponseFriends;
    }

    public PlayListDTO addToPlayList(long id, long musicId){
        Optional<PlayList> playListOpt = playListRepository.findById(id);
        Optional<Music> musicOpt = musicRepository.findById(musicId);
        if(playListOpt.isEmpty())
            throw new Error();
        if(musicOpt.isEmpty())
            throw new Error();
        PlayList playList = playListOpt.get();
        Music music = musicOpt.get();
        List<Music> musicList = playList.getSongList();
        musicList.add(music);
        playList.setSongList(musicList);
        playList = playListRepository.save(playList);
        PlayListDTO playListDTO = new PlayListDTO(playList.getPlayListId(), playList.getName(), playList.getPicture(), playList.getSongList());
        return playListDTO;
    }


    public CheckInResponse checkInValidation(long costumerId, long houseId){
        Optional<House> houseOpt = houseRepository.findById(houseId);
        if(houseOpt.isEmpty())
            throw new Error();
        Optional<User> costumerOpt = userRepository.findById(costumerId);
        if(costumerOpt.isEmpty())
            throw new Error();
        House house = houseOpt.get();
        if(house.isOpen() == false)
            throw new Error();
        User costumer = costumerOpt.get();
        List<User> costumersIn = house.getCostumersIn();
        costumersIn.add(costumer);
        house.setCostumersIn(costumersIn);
        costumer.setHouse(house);
        houseRepository.save(house);
        userRepository.save(costumer);
        CheckInResponse checkInResponse = new CheckInResponse(house.getHouseId(), costumer.getUserId(), house.getHouseName(), costumer.getName());
        return checkInResponse;
    }

    public String openBar(long id){
        Optional<House> houseOpt = houseRepository.findById(id);
        if(houseOpt.isEmpty())
            throw new Error();
        House house = houseOpt.get();
        house.setOpen(true);
        houseRepository.save(house);
        return "The Bar is open";
    }

    public String closeBar(long id){
        Optional<House> houseOpt = houseRepository.findById(id);
        if(houseOpt.isEmpty())
            throw new Error();
        House house = houseOpt.get();
        house.setOpen(false);
        houseRepository.save(house);
        return "The Bar is closed";
    }

    public void removeMusicFromPlaylist(long musicId, long playlistId){
        Optional<Music> musicOpt = musicRepository.findById(musicId);
        if(musicOpt.isEmpty())
            throw new Error();
        Optional<PlayList> playListOpt = playListRepository.findById(playlistId);
        if(playListOpt.isEmpty())
            throw new Error();
        Music music = musicOpt.get();
        PlayList playList = playListOpt.get();
        List<Music> musicList = playList.getSongList();
        musicList.remove(music);
        playList.setSongList(musicList);
        playListRepository.save(playList);
    }

    public void removeMusicFromFav(long musicId, long userId){
        Optional<Music> musicOpt = musicRepository.findById(musicId);
        if(musicOpt.isEmpty())
            throw new Error();
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isEmpty())
            throw new Error();
        Music music = musicOpt.get();
        User user = userOpt.get();
        List<FavMusic> musicList = user.getFavMusicList();
        musicList.remove(music);
        user.setFavMusicList(musicList);
        userRepository.save(user);
    }

    public List<UserResponseCostumer> removeFromNextSinger(long houseId, long userId){
        Optional<House> houseOpt = houseRepository.findById(houseId);
        if(houseOpt.isEmpty())
            throw new Error();
        Optional<User> costumerOpt = userRepository.findById(userId);
        if(costumerOpt.isEmpty())
            throw new Error();
        User user = costumerOpt.get();
        House house = houseOpt.get();
        List<User> nextSinger = house.getNextSingerList();
        nextSinger.remove(user);
        house.setNextSingerList(nextSinger);
        user.setNextMusic(null);
        userRepository.save(user);
        houseRepository.save(house);
        List<UserResponseCostumer> costumerList = new ArrayList<>();
        for(User singer:nextSinger){
            UserResponseCostumer costumer = new UserResponseCostumer(singer.getUserId(),singer.getName(),singer.getLastName(),singer.getBirthDay(),singer.getGender()
                    ,singer.getAddress(),singer.getPhone(),singer.getPicture());
            costumerList.add(costumer);
        }
        return costumerList;
    }

    public void deleteUserAccount(long id){
       Optional<User> userOpt = userRepository.findById(id);
       if(userOpt.isEmpty())
           throw new Error();
       User user = userOpt.get();
       user.setHouse(null);
       user.setNextMusic(null);
       user.setFriends(null);
       user.setRequestReceived(null);
       user.setPlayList(null);
       user.setNextMusic(null);
       user.setFavMusicList(null);
       user.setRequestDispatched(null);
       userRepository.delete(user);
    }
    public void deleteBarAccount(long id){
        Optional<House> houseOpt = houseRepository.findById(id);
        if(houseOpt.isEmpty())
            throw new Error();
        House house = houseOpt.get();
        house.setNextSingerList(null);
        house.setMusicOutOfList(null);
        house.setMusicInList(null);
        house.setCostumersIn(null);
        houseRepository.delete(house);
    }

    public void deletePlayList(long playListId){
        Optional<PlayList> playListOpt = playListRepository.findById(playListId);
        if(playListOpt.isEmpty())
            throw new Error();
        PlayList playList = playListOpt.get();
        playList.setSongList(null);
        playListRepository.delete(playList);
    }
}
