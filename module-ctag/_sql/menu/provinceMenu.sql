-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('省别', '2000', '1', 'province', 'ctag/province/index', 1, 'C', '0', '0', 'ctag:province:list', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '省别菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('省别查询', @parentId, '1',  '#', '', 1,  'F', '0',  '0', 'ctag:province:query',        '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('省别新增', @parentId, '2',  '#', '', 1,  'F', '0',  '0', 'ctag:province:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('省别修改', @parentId, '3',  '#', '', 1,  'F', '0',  '0', 'ctag:province:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('省别删除', @parentId, '4',  '#', '', 1,  'F', '0',  '0', 'ctag:province:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('省别导出', @parentId, '5',  '#', '', 1,  'F', '0',  '0', 'ctag:province:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
