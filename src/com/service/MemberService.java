package com.service;

import com.dao.MemberDao;
import com.model.Member;

import java.util.List;

public class MemberService {
    private MemberDao memberDao = new MemberDao();

    // Step 1: Prepare Member object
    // Step 2: Save member to DB using dao
    public void save(Member member) {
        memberDao.save(member);
    }

    // Step 1: Fetch member from DB by id using dao
    public Member findById(int id) {
        return memberDao.findById(id);
    }

    // Step 1: Fetch all members from DB using dao
    public List<Member> findAll() {
        return memberDao.findAll();
    }
}