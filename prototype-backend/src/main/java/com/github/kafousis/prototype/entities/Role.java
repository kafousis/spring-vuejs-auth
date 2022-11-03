package com.github.kafousis.prototype.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.Accessors;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name = "roles")
@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode @ToString @Accessors(chain = true)
@SequenceGenerator(name = "role_generator", sequenceName = "role_id_seq", allocationSize = 1)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_generator")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = { @JoinColumn(name = "role_id") },
            inverseJoinColumns = { @JoinColumn(name = "privilege_id") }
    )
    private Set<Privilege> privileges = new HashSet<>();

    // @EqualsAndHashCode.Exclude prevents 'collection evicted' when fetching
    // @JsonBackReference marks the field as non-serializable

    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "role")
    private Set<User> users = new HashSet<>();
}
