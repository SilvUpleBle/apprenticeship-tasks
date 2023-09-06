package org.task_4.service;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.task_4.database.model.Citizen;
import org.task_4.database.repository.CitizenRepo;
import org.task_4.exceptions.NotFindException;
import org.task_4.exceptions.UniqueException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@NoArgsConstructor
public class CitizenService {
    @Autowired
    CitizenRepo citizensRepo;

    public Citizen saveCitizen(Citizen citizenIncome) throws UniqueException {
        Citizen citizen = Citizen.builder()
                .lastName(citizenIncome.getLastName())
                .firstName(citizenIncome.getFirstName())
                .middleName(citizenIncome.getMiddleName())
                .birthDate(citizenIncome.getBirthDate())
                .phone(citizenIncome.getPhone())
                .extraPhone(citizenIncome.getExtraPhone())
                .dulSerie(citizenIncome.getDulSerie())
                .dulNumber(citizenIncome.getDulNumber())
                .build();
        if(citizensRepo.findListOfCitizensByOptionalParams(citizen.getLastName(),citizen.getFirstName(),
                citizen.getMiddleName(), citizen.getBirthDate()).size() >= 1){
            throw new UniqueException("Пользователь с введенными вами данными уже существует");
        }
        return citizensRepo.save(citizen);
    }

    public List<Citizen> getAllCitizensByParams(Citizen citizen) throws NotFindException {
        var citizenList = citizensRepo.findListOfCitizensByOptionalParams(citizen.getLastName(), citizen.getFirstName(),
                citizen.getMiddleName(), citizen.getBirthDate());
        if(citizenList.size() == 0) throw new NotFindException("По данным параметрам гражданин не найден.");
        return citizenList;
    }

    public Citizen getOneCitizen(Long id) throws NotFindException {
        try {
            Citizen citizen = citizensRepo.findById(id).get();
            return citizen;
        } catch (NoSuchElementException e){
            throw new NotFindException("По данному id гражданин не найден.");
        }
    }

    @SneakyThrows({NoSuchMethodException.class, InvocationTargetException.class, IllegalAccessException.class})
    public void updateCitizenByIdAndParams(Long id, Citizen citizen) throws NotFindException {
        try {
            Citizen citizenForUpdate = citizensRepo.findById(id).get();
            Field[] fields = citizen.getClass().getDeclaredFields();
            Field[] fieldsForUpdate = citizenForUpdate.getClass().getDeclaredFields();
            ArrayList<Field> utilList = new ArrayList<Field>(Arrays.asList(fields));
            List<StringBuilder> sbFields = new ArrayList<>();
            for (int i = 1; i < utilList.size();i++) {
                sbFields.add(new StringBuilder(String.valueOf(utilList.get(i).getName())));
                Character firstChar = sbFields.get(i - 1).charAt(0);
                firstChar = (char) (firstChar & 0x5f);
                sbFields.get(i - 1).setCharAt(0,firstChar);
            }
            for (int i = 0; i < sbFields.size(); i++) {
                Method metGet = citizen.getClass().getMethod("get"+sbFields.get(i));
                metGet.setAccessible(true);
                if(metGet.invoke(citizen) != null){
                    Method metSet;
                    if(i <= 5)//после 5 метода наш методы получают integer
                    {
                        metSet = citizen.getClass().getMethod("set"+sbFields.get(i), String.class);
                    }
                    else {
                        metSet = citizen.getClass().getMethod("set"+sbFields.get(i), Integer.class);
                    }
                    metSet.setAccessible(true);
                    metSet.invoke(citizenForUpdate,metGet.invoke(citizen));
                }
            }
            if(citizensRepo.findListOfCitizensByOptionalParams(citizenForUpdate.getLastName(), citizenForUpdate.getFirstName(),
                    citizenForUpdate.getMiddleName(),citizenForUpdate.getBirthDate()).size() > 1)
                throw new UniqueException("По введенным данным уже существует гражданин");
            else{
                citizensRepo.save(citizenForUpdate);
            }
        } catch (NoSuchElementException | UniqueException e) {
            throw new NotFindException("По данным параметрам гражданин не найден.");
        }
    }

    public void deleteCitizenById(Long id) throws NotFindException {
        try{
            Citizen citizen = citizensRepo.findById(id).get();
            citizensRepo.delete(citizen);
        }catch (NoSuchElementException e){
            throw new NotFindException("Гражданин не удален, тк в базе данных нет данного id");
        }

    }
}
