package com.infobezb.demo.service;

import com.infobezb.demo.model.Candidate;
import com.infobezb.demo.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Long getTotalNumberOfVotes() {
        List<Candidate> candidates = candidateRepository.findAll();
        return candidates.stream().mapToLong(Candidate::getNumberOfVotes).sum();
    }
}
