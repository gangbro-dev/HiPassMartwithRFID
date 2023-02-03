package e101.hishop.service;

import e101.hishop.repository.ProductJPARepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class IotServicelmpl implements IotService{

    private final ProductJPARepository productJPARepository;
    private final AdminService adminService;


}
