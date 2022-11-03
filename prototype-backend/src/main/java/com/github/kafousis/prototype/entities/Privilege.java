package com.github.kafousis.prototype.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.kafousis.prototype.enums.PrivilegeCategory;
import lombok.*;
import lombok.experimental.Accessors;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name = "privileges")
@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode @ToString @Accessors(chain = true)
@SequenceGenerator(name = "privilege_generator", sequenceName = "privilege_id_seq", allocationSize = 1)
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "privilege_generator")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PrivilegeCategory category;

    // @EqualsAndHashCode.Exclude prevents 'collection evicted' when fetching
    // @JsonBackReference marks the field as non-serializable

    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "privileges")
    private Set<Role> roles = new HashSet<>();
}
