package com.example.myShop.repository;

import com.example.myShop.dto.MemberFormDto;
import com.example.myShop.entity.Cart;
import com.example.myShop.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class CartTest {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @PersistenceContext
    EntityManager em;
    public Member createMember(){
        MemberFormDto memberFormDto = new MemberFormDto();
      memberFormDto.setEmail("test@email.com");
      memberFormDto.setName("홍길동");
      memberFormDto.setAddress("서울시 마포구 합정동");
      memberFormDto.setPassword("1234");
      return Member.createMember(memberFormDto, passwordEncoder);
    }
    @Test
    @DisplayName("장바구니 회원 엔티티 매핑 조회 테스트")
    public void findCartAndMemberTest(){
        Member member = createMember();
        memberRepository.save(member);
        Cart cart = new Cart();
        cart.setMember(member);
        cartRepository.save(cart);
        em.flush(); //JPA는 영속성 컨텍스트에 데이터를 저장 후 트랜잭션이 끝날 때 flush()를 호출하여 디비에 반영
        em.clear();//JPA는 영속성 컨텍스트로부터 엔티티를 조회 후 영속성 컨텍스트에 엔티티가 없을 경우 데이터베이스를 조회
        Cart savedCart = cartRepository.findById(cart.getId()) // 저장된 장바구니 엔티티 조회. cart와 member 조인
                .orElseThrow(EntityNotFoundException::new);
        assertEquals(savedCart.getMember().getId(),member.getId());
    }
}
