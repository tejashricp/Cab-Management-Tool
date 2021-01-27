package com.cab.management.repository;

import com.cab.management.model.CabSnapshot;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.commons.io.IOUtils;

@Component
public class CabSnapshotRepository extends CustomRepository<CabSnapshot> {

}
