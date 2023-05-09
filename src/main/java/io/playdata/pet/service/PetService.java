package io.playdata.pet.service;

import io.playdata.pet.model.Pet;
import io.playdata.pet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class PetService {
    @Autowired // 자동불러오기
    private PetRepository petRepository;

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public void savePet(Pet pet) {
        petRepository.save(pet);
    }

    public Pet getPet(Long id) {
        return petRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid pet Id:" + id)
        );
    }

    public void updatePet(Pet pet) {
        petRepository.save(pet);
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }

    @Value("${upload.path}") // application.properties에 지정
    private String uploadPath;

    // 메소드 오버로딩 (패러미터를 다르게 해서 다른 것 취급)
    public void savePet(Pet pet, MultipartFile imageFile, MultipartFile voiceFile) throws IOException {
        // imageFile이 비어있지 않으면
        if (imageFile != null) {
            // 겹칠 수 있기 때문에 현재 시스템의 시간(currentTimeMillis)과 파일명을 합쳐서
            // 새로운 파일이름(fileName) 만들기
            String fileName = System.currentTimeMillis() + "-" + imageFile.getOriginalFilename();
            // 업로드를 받을 폴더 경로와 파일명을 합쳐서, 새로운 파일이 들어갈 최종 경로
            File file = new File(uploadPath + "/" + fileName);
            imageFile.transferTo(file); // transferTo -> 전송받은 imageFile을 file 위치에 생성
            pet.setImage(fileName); // 그 파일 이름을 db에 저장하기 위해 image 속성에 파일명을 넣습니다
        }
        // voiceFile이 비어있지 않으면
        if (voiceFile != null) {
            // 겹칠 수 있기 때문에 현재 시스템의 시간(currentTimeMillis)과 파일명을 합쳐서
            // 새로운 파일이름(fileName) 만들기
            String fileName = System.currentTimeMillis() + "-" + voiceFile.getOriginalFilename();
            // 업로드를 받을 폴더 경로와 파일명을 합쳐서, 새로운 파일이 들어갈 최종 경로
            File file = new File(uploadPath + "/" + fileName);
            voiceFile.transferTo(file); // transferTo -> 전송받은 voiceFile을 file 위치에 생성
            pet.setVoice(fileName); // 그 파일 이름을 db에 저장하기 위해 voice 속성에 파일명을 넣습니다
        }

        petRepository.save(pet);
    }
}
