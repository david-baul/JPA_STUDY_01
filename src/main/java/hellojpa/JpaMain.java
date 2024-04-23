package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        //code
        EntityTransaction ts = em.getTransaction();
        ts.begin();

        try{
           //생성자 있을경우
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");
            em.persist(member1);
            em.persist(member2);
            System.out.println("===============");

            /*생성자 없을경우
            Member member1 = new Member();
            member1.setId(150L);
            member1.setName("A");

            Member member2 = new Member();
            member2.setId(160L);
            member2.setName("B");
            */

/*          //em에서 불러왔기에 persistantce를 안해줘도 영속상태
            Member findMember = em.find(Member.class, 1L);
            //수정
            //findMember.setName("HelloJPA");

            //삭제
            //em.remove(findMember);

            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();
            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
            }
*/

/*

            //기본로직
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");
            // 여기까지는 비영속상태

            em.persist(member); // 영속상태, em(엔티티 메니저)안에있는 영속석 context라는곳을 통해 멤버가 관리된다는뜻

            em.detach(member); // 회원 엔티티를 영속성 컨텍스트에서 분리

            em.remove(member); // db에서 삭제
*/


            ts.commit();
        }catch (Exception e){
            ts.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }
}
